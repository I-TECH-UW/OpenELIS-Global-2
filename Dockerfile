##
# Build Stage
#
FROM maven:3-jdk-11 AS build

RUN --mount=target=/var/lib/apt/lists,type=cache,sharing=locked \
    --mount=target=/var/cache/apt,type=cache,sharing=locked \
    rm -f /etc/apt/apt.conf.d/docker-clean \
    && apt-get -y update \
    && apt-get -y --no-install-recommends install \
    git apache2-utils nodejs npm


# OE Default Password
ARG DEFAULT_PW="adminADMIN!"
COPY ./install/createDefaultPassword.sh /build/install/createDefaultPassword.sh
WORKDIR /build
RUN ./install/createDefaultPassword.sh -c -p ${DEFAULT_PW}
## 
# Building the project
#
WORKDIR /build

COPY ./pom.xml /build/pom.xml
RUN --mount=type=cache,target=/root/.m2,sharing=locked \
    mvn dependency:go-offline 

ARG SKIP_SPOTLESS="false"
COPY ./src /build/src
RUN --mount=type=cache,target=/root/.m2,sharing=locked \
    mvn clean install -DskipTests -Dspotless.check.skip=${SKIP_SPOTLESS}

##
# Run Stage
#
FROM tomcat:8.5-jdk11

COPY install/createDefaultPassword.sh ./


#Clean out unneccessary files from tomcat (especially pre-existing applications) 
RUN rm -rf /usr/local/tomcat/webapps/* \ 
    /usr/local/tomcat/conf/Catalina/localhost/manager.xml

#Deploy the war into tomcat image and point root to it
COPY install/tomcat-resources/ROOT.war /usr/local/tomcat/webapps/ROOT.war
COPY --from=build /build/target/OpenELIS-Global.war /usr/local/tomcat/webapps/OpenELIS-Global.war

#rewrite cataline.properties with our catalina.properties so it contains:
#    org.apache.catalina.STRICT_SERVLET_COMPLIANCE=true
#    org.apache.catalina.connector.RECYCLE_FACADES=true
#    org.apache.catalina.connector.CoyoteAdapter.ALLOW_BACKSLASH=false
#    org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=false
#    org.apache.coyote.USE_CUSTOM_STATUS_MSG_IN_HEADER=false
COPY install/tomcat-resources/catalina.properties /usr/local/tomcat/conf/catalina.properties
COPY install/tomcat-resources/logging.properties /usr/local/tomcat/conf/logging.properties

#replace ServerInfo.properties with a less informative one
RUN mkdir -p /usr/local/tomcat/lib/org/apache/catalina/util
COPY install/tomcat-resources/ServerInfo.properties /usr/local/tomcat/lib/org/apache/catalina/util/ServerInfo.properties 

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
    chmod g-w,o-rwx $CATALINA_HOME/conf/web.xml; \
    mkdir -p /var/lib/openelis-global/logs/; \
    chown -R tomcat_admin:tomcat /var/lib/openelis-global/logs/;\
    mkdir -p /var/lib/openelis-global/properties/; \
    chown -R tomcat_admin:tomcat /var/lib/openelis-global/properties/;


COPY install/openelis_healthcheck.sh /healthcheck.sh
RUN chown tomcat_admin:tomcat /healthcheck.sh; \
    chmod 770 /healthcheck.sh;  

COPY install/docker-entrypoint.sh /docker-entrypoint.sh
RUN chown tomcat_admin:tomcat /docker-entrypoint.sh; \
    chmod 770 /docker-entrypoint.sh;

RUN mkdir -p /var/lib/lucene_index; \
    chown -R tomcat_admin:tomcat /var/lib/lucene_index; \
    chmod -R 770 /var/lib/lucene_index;

USER tomcat_admin

ENTRYPOINT [ "/docker-entrypoint.sh" ]

