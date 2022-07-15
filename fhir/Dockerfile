FROM hapiproject/hapi:v5.4.1

RUN groupadd tomcat; \
    groupadd tomcat-ssl-cert -g 8443; \ 
    useradd -M -s /bin/bash -u 8443 tomcat_admin; \
    usermod -a -G tomcat,tomcat-ssl-cert tomcat_admin; \
USER tomcat

RUN apt-get update && apt-get install -y curl=7.68.0-1ubuntu2.12
