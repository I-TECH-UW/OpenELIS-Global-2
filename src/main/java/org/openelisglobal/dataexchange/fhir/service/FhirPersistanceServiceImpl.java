package org.openelisglobal.dataexchange.fhir.service;

import java.util.List;
import java.util.UUID;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

@Service
public class FhirPersistanceServiceImpl implements FhirPersistanceService {

    private FhirContext fhirContext = SpringContext.getBean(FhirContext.class);
    private FhirConfig fhirConfig = SpringContext.getBean(FhirConfig.class);
    IGenericClient localFhirClient = fhirContext.newRestfulGenericClient(fhirConfig.getLocalFhirStorePath());

    @Override
    public Bundle createFhirResourceInFhirStore(Resource resource) {
        Bundle bundle = new Bundle();
        Bundle resp = new Bundle();
        String resourceType = resource.getResourceType().toString();
        String id = UUID.randomUUID().toString();
//        resource.setId(IdType.newRandomUuid());
//        resource.setIdElement(IdType.newRandomUuid());

        if (ResourceType.Patient.toString().equalsIgnoreCase(resourceType)) {
            ((Patient) resource)
                    .addIdentifier(new Identifier().setValue(id).setSystem(fhirConfig.getOeFhirSystem() + "/pat_guid"));
        }

        try {

            bundle.addEntry().setFullUrl(resource.getIdElement().getValue()).setResource(resource).getRequest()
                    .setUrl(resourceType + "/" + id).setMethod(Bundle.HTTPVerb.PUT);

            LogEvent.logDebug(this.getClass().getName(), "CreateFhirResource", "CreateFhirResource: "
                    + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));

            resp = localFhirClient.transaction().withBundle(bundle).execute();
        } catch (Exception e) {
            LogEvent.logError(e);
        }
        return resp;
    }

    @Override
    public Bundle updateFhirResourceInFhirStore(Resource resource) {
        Bundle bundle = new Bundle();
        Bundle resp = new Bundle();
        try {
            bundle.addEntry().setFullUrl(resource.getIdElement().getValue()).setResource(resource).getRequest()
                    .setUrl(resource.getResourceType() + "/" + resource.getIdElement().getIdPart())
                    .setMethod(Bundle.HTTPVerb.PUT);
            LogEvent.logDebug(this.getClass().getName(), "updateFhirResourcesInFhirStore", "UpdateFhirResource: "
                    + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));
            resp = localFhirClient.transaction().withBundle(bundle).execute();
        } catch (Exception e) {
            LogEvent.logError(e);
        }
        return resp;

    }

    @Override
    public Bundle updateFhirResourcesInFhirStore(List<Resource> resources) {
        Bundle bundle = new Bundle();
        Bundle resp = new Bundle();
        for (Resource resource : resources) {
            bundle.addEntry().setFullUrl(resource.getIdElement().getValue()).setResource(resource).getRequest()
                    .setUrl(resource.getResourceType() + "/" + resource.getIdElement().getIdPart())
                    .setMethod(Bundle.HTTPVerb.PUT);
        }
        try {
            resp = localFhirClient.transaction().withBundle(bundle).execute();
        } catch (Exception e) {
            LogEvent.logError(e);
        }
        return resp;
    }

    @Override
    public Bundle createFhirResourcesInFhirStore(List<Resource> resources) {
        Bundle bundle = new Bundle();
        Bundle resp = new Bundle();
        for (Resource resource : resources) {
            String id = UUID.randomUUID().toString();
            String resourceType = resource.getResourceType().toString();
            if (ResourceType.Patient.toString().equalsIgnoreCase(resourceType)) {
                ((Patient) resource).addIdentifier(
                        new Identifier().setValue(id).setSystem(fhirConfig.getOeFhirSystem() + "/pat_guid"));
            }
            bundle.addEntry().setFullUrl(resource.getIdElement().getValue()).setResource(resource).getRequest()
                    .setUrl(resourceType + "/" + id).setMethod(Bundle.HTTPVerb.PUT);

            LogEvent.logDebug(this.getClass().getName(), "CreateFhirResource", "CreateFhirResource: "
                    + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));
        }
        try {
            resp = localFhirClient.transaction().withBundle(bundle).execute();
        } catch (Exception e) {
            LogEvent.logError(e);
        }
        return resp;
    }
}
