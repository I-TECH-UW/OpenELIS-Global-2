#!/bin/bash

#
# This file exists because we must partially setup the host so docker is able to read the
# certificates used for ssl. By default these are the snakeoil certs, but replacing these files in 
# /etc/tomcat/ssl/ will let us use any certificates we can drop in there
#

#create tomcat_admin user and group if not exists
#MUST BE SAME UID AND GID AS IN DOCKERFILE
groupadd -g 8443 tomcat-ssl-cert || echo "tomcat-ssl-cert group already exists. Continuing"
useradd -u 8443 -g tomcat-ssl-cert tomcat_admin || echo "tomcat_admin user already exists. Continuing"

#create folders for web certificates
mkdir -m 755 -p /etc/tomcat/ssl/certs
mkdir -m 710 -p /etc/tomcat/ssl/private

DEST_CERT_FILE=/etc/tomcat/ssl/certs/tomcat_cert.crt
DEST_KEY_FILE=/etc/tomcat/ssl/private/tomcat_cert.key
if [ ! -f "$DEST_CERT_FILE" ]; then
	echo "creating ${DEST_CERT_FILE}"
	cp /etc/ssl/certs/ssl-cert-snakeoil.pem ${DEST_CERT_FILE}
fi
if [ ! -f "$DEST_KEY_FILE" ]; then
	echo "creating ${DEST_KEY_FILE}"
	cp /etc/ssl/private/ssl-cert-snakeoil.key ${DEST_KEY_FILE}
fi


#MUST BE SAME DESTINATIONS AS IN docker-compose.yaml
DEST_KEYSTORE_FILE=/etc/openelis-global/keystore
DEST_TRUSTSTORE_FILE=/etc/openelis-global/truststore
if [ ! -f "$DEST_KEY_FILE" ]; then
	echo "creating ${DEST_KEY_FILE}"
	cp /etc/ssl/private/ssl-cert-snakeoil.key ${DEST_KEY_FILE}
fi

chown -R root:root /etc/tomcat/ssl
chown -R root:tomcat-ssl-cert /etc/tomcat/ssl/private

chmod 644 ${DEST_CERT_FILE}
chmod 640 ${DEST_KEY_FILE}
