# Property files for server configuration
OpenELIS reads in properties from a few different sources. The property will be set to the last specified source
* /src/main/resources/application.properties - contained in the docker container when compiled
* /var/lib/openelisglobal/secrets/common.properties - overwritten every time setup script is called based on values specified to script
* /var/lib/openelis-global/secrets/extra.properties - optional file for specifying attributes outside of setup script that is persistent 

If you are looking at manually adding/setting a property (ie a facility fhir repo), it should be added to extra.properties

# common.properties
These are the properties that are set in common.properties. Most values are stored and read from the config directory at /var/lib/openelis-global/config/
* server.ssl.key-store - the keystore location inside the docker container

* server.ssl.key-store-password - keystore password collected from the user and persisted in config directory as KEYSTORE_PASSWORD

* server.ssl.key-password - key password collected from the user and persisted in config directory as KEYSTORE_PASSWORD

* server.ssl.trust-store - the truststore location inside the docker container

* server.ssl.trust-store-password - truststore password collected from the user and persisted in config directory as TRUSTSTORE_PASSWORD

* encryption.general.password - encrytption key for storing encrypted values in db and persisted in config directory as ENCRYPTION_KEY

* spring.datasource.driver 

* spring.datasource.url - database url. These values are set from /etc/openelis-global/setup.ini

* spring.datasource.username

* spring.datasource.password - datasource password. These values are set from /var/lib/openelis-global/secrets/datasource.password

* org.openelisglobal.fhirstore.uri - the local fhir server (normally https://fhir.openelis.org:8443/fhir/)

* org.openelisglobal.remote.source.uri - the remote fhir server where orders are imported from (ie the CS) and persisted in config directory as REMOTE_FHIR_SOURCE

* org.openelisglobal.remote.source.updateStatus - normally true, sets whether OE should update the remote instance when it imports orders (move status from requested to accepted)

* org.openelisglobal.remote.source.identifier - the identifier(s) that this OE instance will try to import orders for (ie Organization/uuid) and persisted in config directory as FHIR_IDENTIFIER

* org.openelisglobal.task.useBasedOn

* org.openelisglobal.fhir.subscriber - the server where FHIR objects should be sent to (ie the CS) and persisted in config directory as CS_SERVER

* org.openelisglobal.fhir.subscriber.resources - the FHIR resources that are sent to the subscriber

# extra.properties
As the last loaded file, properties set here will overwrite ones set in other files. As such, if it seems that the values in the config directory are not being used, it could be that different values are being supplied in extra.properties


full properties list:
property | default | description | values
--- | --- | --- | ---
|org.openelisglobal.ozeki.active|false|whether ozeki SMS db should be used|true,false|
|org.openelisglobal.mail.bcc|safemauritius|bcc for email|user@email.com|
|org.openelisglobal.mail.from|Ahl-lab@safemauritius.govmu.org|from for email|user@email.com|
|org.openelisglobal.notification.sms.sender|Covid Lab|the sender used for SMS messages||
|org.openelisglobal.smsc.serviceType|CMT|Used for SMPP sms sending||
|org.openelisglobal.smsc.bindParamSystemType||used for SMPP sms sending||
|org.openelisglobal.externalSearch.infohighway.timeout|50000|timeout to reach the infohighway|integer|
|org.openelisglobal.externalSearch.timeout|5000|timeout for all other patient searches|integer|
|org.openelisglobal.task.useBasedOn||use based-on resources for order imports [DEPRECATED]|true,false|
|org.openelisglobal.paging.results.pageSize|99|results page size|integer|
|org.openelisglobal.paging.validation.pageSize|99|validation page size|integer|
|org.openelisglobal.facilitylist.fhirstore||facility list fhirstore url|url|
|org.openelisglobal.facilitylist.authurl||facility auth url for token based auth|url|
|org.openelisglobal.facilitylist.username||facility auth username||
|org.openelisglobal.facilitylist.password||facility auth password||
|org.openelisglobal.facilitylist.auth|basic|facility auth type |[basic, token]|
|org.openelisglobal.fhirstore.uri||local fhir url|url|
|org.openelisglobal.remote.source.updateStatus||update status of imported tasks in remote source|true,false|
|org.openelisglobal.remote.source.identifier||identifier(s) for importing tasks from remote source|ResourceType/uuid|
|org.openelisglobal.oe.fhir.system|http://openelis-global.org|the system that will be used for OE identifiers in FHIR|url|
|org.openelisglobal.remote.source.uri||remote fhir server url to import tasks from|url|
|org.openelisglobal.fhirstore.username||username for basic auth for fhir servers||
|org.openelisglobal.fhirstore.password||passweord for basic auth for fhir servers||
|org.openelisglobal.requester.lastName||default last name used for requester on order entry screen||
|org.openelisglobal.requester.firstName||default first name used for requester on order entry screen||
|org.openelisglobal.requester.phone||default phone number used for requester on order entry screen||
|org.openelisglobal.timezone||timezone that OpenELIS should be running in||
|encryption.general.password|dev|encryption key||
|server.ssl.trust-store||path to the truststore||
|server.ssl.trust-store-password||truststore password||
|server.ssl.key-store||path to keystore||
|server.ssl.key-store-password||keystore password||
|server.ssl.key-password||key password within truststore (normally the same as password for keystore)||
|org.openelisglobal.fhir.subscriber||FHIR server where FHIR resources are sent (CS)|url|
|org.openelisglobal.fhir.subscriber.allowHTTP|FALSE|allow standard HTTP for backing up (not recommended)|true, false|
|org.openelisglobal.fhir.subscriber.resources||resources that should be sent to the subscriber FHIR server|for a list of fhir resources visit https://www.hl7.org/fhir/resourcelist.html|
|org.openelisglobal.fhir.subscriber.backup.interval|5|the interval upon which backup will be attemtped in minutes|integers|
|org.openelisglobal.fhir.subscriber.backup.timeout|60|the timeout for backing up  in seconds|integers|
|org.openelisglobal.fhir.subscriber.resource.singleTransaction|FALSE|whether a single transaction should be used for backing up resources|true, false|
