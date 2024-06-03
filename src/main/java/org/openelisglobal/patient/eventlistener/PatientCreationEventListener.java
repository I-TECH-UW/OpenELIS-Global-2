package org.openelisglobal.patient.eventlistener;

import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.apache.log4j.Logger;
import org.hl7.fhir.r4.model.Patient;
import org.openelisglobal.dataexchange.fhir.exception.FhirPersistanceException;
import org.openelisglobal.dataexchange.fhir.exception.FhirTransformationException;
import org.openelisglobal.dataexchange.fhir.service.FhirPersistanceService;
import org.openelisglobal.dataexchange.fhir.service.FhirTransformService;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A listener that listens for the PatientCreationEvent
 * @see PatientCreationEvent
 */
@SuppressWarnings("unused")
@Component
public class PatientCreationEventListener {
    private final static Logger log = Logger.getLogger(PatientCreationEventListener.class);

    @Autowired
    FhirTransformService fhirTransformService;

    @Autowired
    FhirPersistanceService fhirPersistanceService;

    @Autowired
    @Qualifier("clientRegistryFhirClient")
    private IGenericClient client;

    @EventListener
    public void handlePatientCreatedEvent(PatientCreationEvent event) throws FhirPersistanceException, FhirTransformationException {
        PatientManagementInfo patientInfo = event.getPatientInfo();
        log.warn("Patient created with ID: " + patientInfo.getPatientPK());

        Optional<Patient> patient = fhirPersistanceService.getPatientByUuid(patientInfo.getGuid());
        try {
            client.create().resource(String.valueOf(patient)).execute();
        }
        catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataFormatException) {
                log.warn(e.getMessage());
            } else {
                throw e;
            }
        }
    }
}
