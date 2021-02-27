package org.openelisglobal.dataexchange.fhir.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.dataexchange.fhir.FhirConfig;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.spring.util.SpringContext;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

@Service
public class FhirPersistanceServiceImpl implements FhirPersistanceService {

    private FhirContext fhirContext = SpringContext.getBean(FhirContext.class);
    private FhirConfig fhirConfig = SpringContext.getBean(FhirConfig.class);
    private FhirUtil fhirUtil = SpringContext.getBean(FhirUtil.class);
    IGenericClient localFhirClient = fhirUtil.getFhirClient(fhirConfig.getLocalFhirStorePath());

    @Override
    public Bundle createFhirResourceInFhirStore(Resource resource) {
        Bundle transactionBundle = makeTransactionBundleForCreate(Arrays.asList(resource));
        Bundle transactionResponseBundle = new Bundle();
        try {
            transactionResponseBundle = localFhirClient.transaction().withBundle(transactionBundle).execute();
        } catch (Exception e) {
            LogEvent.logError(e);
        }
        return transactionResponseBundle;
    }

    @Override
    public Bundle createFhirResourcesInFhirStore(List<Resource> resources) {
        Bundle transactionBundle = makeTransactionBundleForCreate(resources);
        Bundle transactionResponseBundle = new Bundle();
        try {
            transactionResponseBundle = localFhirClient.transaction().withBundle(transactionBundle).execute();
        } catch (Exception e) {
            LogEvent.logError(e);
        }
        return transactionResponseBundle;
    }

    @Override
    public Bundle updateFhirResourceInFhirStore(Resource resource) {
        Bundle transactionBundle = makeTransactionBundleForUpdate(Arrays.asList(resource));
        Bundle transactionResponseBundle = new Bundle();
        try {
            transactionResponseBundle = localFhirClient.transaction().withBundle(transactionBundle).execute();
        } catch (Exception e) {
            LogEvent.logError(e);
        }
        return transactionResponseBundle;

    }

    @Override
    public Bundle updateFhirResourcesInFhirStore(List<Resource> resources) {
        Bundle transactionBundle = makeTransactionBundleForUpdate(resources);
        Bundle transactionResponseBundle = new Bundle();
        try {
            transactionResponseBundle = localFhirClient.transaction().withBundle(transactionBundle).execute();
        } catch (Exception e) {
            LogEvent.logError(e);
        }
        return transactionResponseBundle;
    }

    @Override
    public Bundle makeTransactionBundleForCreate(List<Resource> resources) {
        Bundle transactionBundle = new Bundle();
        for (Resource resource : resources) {
            String id = UUID.randomUUID().toString();
            String resourceType = resource.getResourceType().toString();
            if (ResourceType.Patient.toString().equalsIgnoreCase(resourceType)) {
                ((Patient) resource).addIdentifier(
                        new Identifier().setValue(id).setSystem(fhirConfig.getOeFhirSystem() + "/pat_guid"));
            }
            transactionBundle.addEntry().setFullUrl(resource.getIdElement().getValue()).setResource(resource).getRequest()
                    .setUrl(resourceType + "/" + id).setMethod(Bundle.HTTPVerb.PUT);

//            LogEvent.logDebug(this.getClass().getName(), "makeTransactionBundleForCreate",
//                    "makeTransactionBundleForCreate: "
//                    + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(transactionBundle));
        }
        transactionBundle.setType(BundleType.TRANSACTION);
        return transactionBundle;

    }

    @Override
    public Bundle makeTransactionBundleForUpdate(List<Resource> resources) {
        Bundle transactionBundle = new Bundle();
        for (Resource resource : resources) {
            transactionBundle.addEntry().setFullUrl(resource.getIdElement().getValue()).setResource(resource).getRequest()
                    .setUrl(resource.getResourceType() + "/" + resource.getIdElement().getIdPart())
                    .setMethod(Bundle.HTTPVerb.PUT);

//            LogEvent.logDebug(this.getClass().getName(), "makeTransactionBundleForUpdate",
//                    "makeTransactionBundleForUpdate: "
//                            + fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(transactionBundle));
        }
        transactionBundle.setType(BundleType.TRANSACTION);
        return transactionBundle;

    }

}
