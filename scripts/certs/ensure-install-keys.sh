#!/bin/sh
# Gives this install its own TLS key and keeps the key and trust stores built from it.
#
# Run by the compose `certs` service on every start. The first start generates the
# install's key and certificate under /etc/openelis-global/install, including on an
# install whose stores still hold the key baked into the public certgen image. Later
# starts keep that key. The keystore and truststore are rebuilt when they do not hold
# the install's certificate or no longer open with KEYSTORE_PW and TRUSTSTORE_PW.
# The proxy's certificate files are written only when they are missing or still hold
# the certificate being replaced, so a Let's Encrypt or locally issued one is kept.
set -eu

STORES=/etc/openelis-global
INSTALL="$STORES/install"
KEY="$INSTALL/server.key"
CERT="$INSTALL/server.crt"
PROXY_CERT=/etc/ssl/certs/apache-selfsigned.crt
PROXY_KEY=/etc/ssl/private/apache-selfsigned.key
export KEYSTORE_PW="${KEYSTORE_PW:-kspass}"
export TRUSTSTORE_PW="${TRUSTSTORE_PW:-tspass}"
DAYS="${CERT_VALIDITY_DAYS:-3650}"

fingerprint() {
  openssl x509 -noout -fingerprint -sha256 2>/dev/null | cut -d= -f2
}

keystore_fingerprint() {
  [ -s "$STORES/keystore" ] || return 0
  openssl pkcs12 -in "$STORES/keystore" -passin env:KEYSTORE_PW -nokeys -clcerts 2>/dev/null | fingerprint
}

truststore_fingerprint() {
  [ -s "$STORES/truststore" ] || return 0
  keytool -exportcert -rfc -alias oeCert -keystore "$STORES/truststore" -storetype pkcs12 \
    -storepass:env TRUSTSTORE_PW 2>/dev/null | fingerprint
}

mkdir -p "$INSTALL"
chmod 700 "$INSTALL"

if [ ! -s "$KEY" ] || [ ! -s "$CERT" ]; then
  echo "Generating this install's TLS key and certificate"
  openssl req -x509 -nodes -newkey rsa:2048 -days "$DAYS" \
    -keyout "$KEY.tmp" -out "$CERT.tmp" \
    -subj "/O=OpenELIS Global/CN=localhost" \
    -addext "subjectAltName=DNS:localhost,DNS:*.openelis.org,DNS:*.openelis-global.org,IP:127.0.0.1" 2>/dev/null
  chmod 600 "$KEY.tmp"
  mv "$KEY.tmp" "$KEY"
  mv "$CERT.tmp" "$CERT"
fi

current=$(fingerprint <"$CERT")
previous=$(keystore_fingerprint)

if [ "$previous" != "$current" ]; then
  echo "Building the keystore from this install's key"
  openssl pkcs12 -export -inkey "$KEY" -in "$CERT" -out "$STORES/keystore.tmp" -passout env:KEYSTORE_PW
  mv "$STORES/keystore.tmp" "$STORES/keystore"
  cp "$STORES/keystore" "$STORES/client_facing_keystore"
fi

if [ "$(truststore_fingerprint)" != "$current" ]; then
  echo "Trusting this install's certificate in the truststore"
  if [ -s "$STORES/truststore" ] && keytool -list -keystore "$STORES/truststore" -storetype pkcs12 \
    -storepass:env TRUSTSTORE_PW >/dev/null 2>&1; then
    cp "$STORES/truststore" "$STORES/truststore.tmp"
    keytool -delete -alias oeCert -keystore "$STORES/truststore.tmp" -storetype pkcs12 \
      -storepass:env TRUSTSTORE_PW >/dev/null 2>&1 || true
  else
    rm -f "$STORES/truststore.tmp"
  fi
  keytool -importcert -noprompt -alias oeCert -file "$CERT" -keystore "$STORES/truststore.tmp" \
    -storetype pkcs12 -storepass:env TRUSTSTORE_PW >/dev/null
  mv "$STORES/truststore.tmp" "$STORES/truststore"
fi

chmod a+r "$STORES/keystore" "$STORES/client_facing_keystore" "$STORES/truststore"

served=""
if [ -f "$PROXY_CERT" ] && [ ! -L "$PROXY_CERT" ]; then
  served=$(fingerprint <"$PROXY_CERT")
fi
# Missing, or still the certificate being replaced; a symlink or another certificate is kept.
if [ -d "$(dirname "$PROXY_CERT")" ] && [ -d "$(dirname "$PROXY_KEY")" ] && [ "$served" != "$current" ] && {
  { [ ! -e "$PROXY_CERT" ] && [ ! -L "$PROXY_CERT" ]; } || { [ -n "$previous" ] && [ "$served" = "$previous" ]; }
}; then
  echo "Giving the proxy this install's certificate"
  cp "$CERT" "$PROXY_CERT"
  cp "$KEY" "$PROXY_KEY"
  chmod 644 "$PROXY_CERT"
  chmod 600 "$PROXY_KEY"
fi

echo "TLS stores hold this install's certificate (SHA-256 $current)"
