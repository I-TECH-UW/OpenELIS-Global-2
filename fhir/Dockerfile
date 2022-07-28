FROM hapiproject/hapi:v5.4.1

RUN groupadd tomcat; \
    groupadd tomcat-ssl-cert -g 8443; \ 
    useradd -M -s /bin/bash -u 8443 tomcat_admin; \
    usermod -a -G tomcat,tomcat-ssl-cert tomcat_admin;

RUN apt-get update && apt-get install -y curl=7.64.0-4+deb10u2

USER tomcat_admin
