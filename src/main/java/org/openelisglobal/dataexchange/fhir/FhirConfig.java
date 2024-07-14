package org.openelisglobal.dataexchange.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.rest.client.apache.ApacheRestfulClientFactory;
import ca.uhn.fhir.rest.client.api.IRestfulClientFactory;
import lombok.Getter;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FhirConfig {

    @Getter
    @Value("${org.openelisglobal.oe.fhir.system:http://openelis-global.org}")
    private String oeFhirSystem;

    @Getter
    @Value("${org.openelisglobal.crserver.uri}")
    private String clientRegistryServerUrl;

    @Getter
    @Value("${org.openelisglobal.crserver.username}")
    private String clientRegistryUserName;

    @Getter
    @Value("${org.openelisglobal.crserver.password}")
    private String clientRegistryPassword;

    @Getter
    @Value("${org.openelisglobal.fhirstore.uri}")
    private String localFhirStorePath;

    @Getter
    @Value("${org.openelisglobal.remote.source.uri}")
    private String[] remoteStorePaths;

    @Getter
    @Value("${org.openelisglobal.fhirstore.username:}")
    private String username;

    @Getter
    @Value("${org.openelisglobal.fhirstore.password:}")
    private String password;

    @Autowired
    CloseableHttpClient httpClient;

    @Bean
    public FhirContext fhirContext() {
       FhirContext fhirContext = FhirContext.forR4();
        configureFhirHttpClient(fhirContext);
        return fhirContext;
    }

    public void configureFhirHttpClient(FhirContext fhirContext) {
        IRestfulClientFactory clientFactory = new ApacheRestfulClientFactory(fhirContext);

        clientFactory.setHttpClient(httpClient);
        fhirContext.setRestfulClientFactory(clientFactory);
    }

}
