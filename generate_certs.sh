##
# Prerequesites
#
apt-get update && apt-get upgrade -y && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y \
      openssl net-tools python default-jdk maven \ 
      apache2-utils git apt-transport-https \
      ca-certificates curl gnupg lsb-release software-properties-common\
    && apt-get clean

##
# Certificates
#

# Self-signed Certs
openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /etc/ssl/private/apache-selfsigned.key -out /etc/ssl/certs/apache-selfsigned.crt \ 
    -subj "/C=US/ST=WA/L=Seattle/O=I-TECH-UW/OU=DIGI/CN=localhost"

# Keystore
openssl pkcs12 -inkey /etc/ssl/private/apache-selfsigned.key -in /etc/ssl/certs/apache-selfsigned.crt -export -out ./volume/cert_store/keystore --passin pass:kspass --passout pass:kspass
# # Client-facing Keystore
cp ./volume/cert_store/keystore ./volume/cert_store/client_facing_keystore
# # Truststore
keytool -import -alias oeCert -file /etc/ssl/certs/apache-selfsigned.crt -storetype pkcs12 -keystore ./volume/cert_store/truststore -storepass tspass -noprompt

# uncomment the lines below if you face file permision error
# cd ./volume/cert_store
# sudo chmod -R a+rwx *