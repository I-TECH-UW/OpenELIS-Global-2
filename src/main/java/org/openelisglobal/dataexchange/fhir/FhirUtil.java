package org.openelisglobal.dataexchange.fhir;

import org.apache.commons.validator.GenericValidator;
import org.itech.fhir.dataexport.core.service.FhirClientFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;

@Component
public class FhirUtil implements FhirClientFetcher {

    @Autowired
    private FhirConfig fhirConfig;
    @Autowired
    private FhirContext fhirContext;

    @Override
    public IGenericClient getFhirClient(String fhirStorePath) {
        IGenericClient fhirClient = fhirContext.newRestfulGenericClient(fhirStorePath);
        if (!GenericValidator.isBlankOrNull(fhirConfig.getUsername())
                && !fhirConfig.getLocalFhirStorePath().equals(fhirStorePath)) {
            IClientInterceptor authInterceptor = new BasicAuthInterceptor(fhirConfig.getUsername(),
                    fhirConfig.getPassword());
            fhirClient.registerInterceptor(authInterceptor);
        }

        return fhirClient;
    }

    public IParser getFhirParser() {
        return fhirContext.newJsonParser();
    }

}
