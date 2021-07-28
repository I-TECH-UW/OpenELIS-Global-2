##
# Build Stage
#
FROM ubuntu:focal as build

ENV KEYSTORE_PW="kspass"
ENV TRUSTSTORE_PW="tspass"
ENV DEFAULT_PW="oepass"
ENV INSTALLER_CREATION_DIR="OEInstaller"
ENV STAGING_DIR="OEInstaller_stagingDir"
ENV OE_BRANCH="master"

##
# Prerequesites
#
RUN apt-get update && sudo apt-get upgrade \
    DEBIAN_FRONTEND=noninteractive apt-get install -y \
      openssl net-tools python default-jdk maven \ 
      apache2-utils git printf apt-transport-https \
      ca-certificates curl gnupg-agent software-properties-common\
    && apt-get clean
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
RUN sudo add-apt-repository \
    "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) \
    stable"
RUN sudo apt update
RUN sudo apt install docker-ce docker-ce-cli containerd.io

##
# Certificates
#

# Self-signed Certs
RUN openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout /etc/ssl/private/apache-selfsigned.key -out /etc/ssl/certs/apache-selfsigned.crt
RUN mkdir /etc/openelis-global/
# Keystore
RUN openssl pkcs12 -inkey /etc/ssl/private/apache-selfsigned.key -in /etc/ssl/certs/apache-selfsigned.crt -export -out /etc/openelis-global/keystore --passin env:KEYSTORE_PW
# Client-facing Keystore
RUN cp /etc/openelis-global/keystore /etc/openelis-global/client_facing_keystore
# Truststore
RUN sudo keytool -import -alias oeCert -file /etc/ssl/certs/apache-selfsigned.crt -storetype pkcs12 -keystore /etc/openelis-global/truststore -storepass ${TRUSTSTORE_PW}}

##
# Copy Source Code
#
ADD ./pom.xml /build
ADD ./tools /build/tools
ADD ./src /build/src
ADD ./install /build/install
ADD ./dev /build/dev

WORKDIR /build

# OE Default Password
RUN ${DEFAULT_PW} | ./install/createDefaultPassword.sh

RUN	mvn clean install -DskipTests

##
# Run Stage
#
FROM tomcat:8.5-jdk11




#Clean out unneccessary files from tomcat (especially pre-existing applications) 
RUN rm -rf /usr/local/tomcat/webapps/* \ 
    /usr/local/tomcat/conf/Catalina/localhost/manager.xml
    
#Deploy the war into tomcat image and point root to it
ADD install/tomcat-resources/ROOT.war /usr/local/tomcat/webapps/ROOT.war
COPY --from=build /build/target/OpenELIS-Global.war /usr/local/tomcat/webapps/OpenELIS-Global.war
    
#contains sensitive data, so being mounted at runtime
#ADD ./install/tomcat-resources/server.xml /usr/local/tomcat/conf/server.xml

#rewrite cataline.properties with our catalina.properties so it contains:
#    org.apache.catalina.STRICT_SERVLET_COMPLIANCE=true
#    org.apache.catalina.connector.RECYCLE_FACADES=true
#    org.apache.catalina.connector.CoyoteAdapter.ALLOW_BACKSLASH=false
#    org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=false
#    org.apache.coyote.USE_CUSTOM_STATUS_MSG_IN_HEADER=false
ADD install/tomcat-resources/catalina.properties /usr/local/tomcat/conf/catalina.properties

#replace ServerInfo.properties with a less informative one
RUN mkdir -p /usr/local/tomcat/lib/org/apache/catalina/util
ADD install/tomcat-resources/ServerInfo.properties /usr/local/tomcat/lib/org/apache/catalina/util/ServerInfo.properties 

#restrict files
#GID AND UID must be kept the same as setupTomcat.sh (if using default certificate group)
RUN groupadd tomcat; \
    groupadd tomcat-ssl-cert -g 8443; \ 
    useradd -M -s /bin/bash -u 8443 tomcat_admin; \
    usermod -a -G tomcat,tomcat-ssl-cert tomcat_admin; \
    chown -R tomcat_admin:tomcat $CATALINA_HOME; \
    chmod g-w,o-rwx $CATALINA_HOME; \
    chmod g-w,o-rwx $CATALINA_HOME/conf; \
    chmod o-rwx $CATALINA_HOME/logs; \
    chmod o-rwx $CATALINA_HOME/temp; \
    chmod g-w,o-rwx $CATALINA_HOME/bin; \
    chmod g-w,o-rwx $CATALINA_HOME/webapps; \
    chmod 770 $CATALINA_HOME/conf/catalina.policy; \
    chmod g-w,o-rwx $CATALINA_HOME/conf/catalina.properties; \
    chmod g-w,o-rwx $CATALINA_HOME/conf/context.xml; \
    chmod g-w,o-rwx $CATALINA_HOME/conf/logging.properties; \
    chmod g-w,o-rwx $CATALINA_HOME/conf/server.xml; \
    chmod g-w,o-rwx $CATALINA_HOME/conf/tomcat-users.xml; \
    chmod g-w,o-rwx $CATALINA_HOME/conf/web.xml

ADD install/docker-entrypoint.sh /docker-entrypoint.sh
RUN chown tomcat_admin:tomcat /docker-entrypoint.sh; \
    chmod 770 /docker-entrypoint.sh;
    
USER tomcat_admin

ENTRYPOINT [ "/docker-entrypoint.sh" ]

