package org.openelisglobal.program.service;

import javax.transaction.Transactional;

import org.hl7.fhir.r4.model.Questionnaire;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.program.valueholder.pathology.PathologyCaseViewDisplayItem;
import org.openelisglobal.program.valueholder.pathology.PathologyDisplayItem;
import org.openelisglobal.program.valueholder.pathology.PathologySample;
import org.openelisglobal.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathologyDisplayServiceImpl implements PathologyDisplayService {

    @Autowired
    private SampleService sampleService;
    @Autowired
    private FhirUtil fhirUtil;

    @Override
    @Transactional
    public PathologyDisplayItem convertToDisplayItem(PathologySample pathologySample) {
        PathologyDisplayItem displayItem = new PathologyDisplayItem();
        displayItem.setStatus(pathologySample.getStatus());
        displayItem.setRequestDate(pathologySample.getSample().getEnteredDate());
        if (pathologySample.getPathologist() != null) {
            displayItem.setAssignedPathologist(pathologySample.getPathologist().getDisplayName());
        }
        if (pathologySample.getTechnician() != null) {
            displayItem.setAssignedTechnician(pathologySample.getTechnician().getDisplayName());
        }
        Patient patient = sampleService.getPatient(pathologySample.getSample());
        displayItem.setFirstName(patient.getPerson().getFirstName());
        displayItem.setLastName(patient.getPerson().getLastName());
        displayItem.setLabNumber(pathologySample.getSample().getAccessionNumber());
        displayItem.setPathologySampleId(pathologySample.getId());
        return displayItem;
    }

    @Override
    public PathologyCaseViewDisplayItem convertToCaseDisplayItem(PathologySample pathologySample) {
        PathologyCaseViewDisplayItem displayItem = new PathologyCaseViewDisplayItem();
        displayItem.setStatus(pathologySample.getStatus());
        displayItem.setRequestDate(pathologySample.getSample().getEnteredDate());
        if (pathologySample.getPathologist() != null) {
            displayItem.setAssignedPathologist(pathologySample.getPathologist().getDisplayName());
        }
        if (pathologySample.getTechnician() != null) {
            displayItem.setAssignedTechnician(pathologySample.getTechnician().getDisplayName());
        }
        Patient patient = sampleService.getPatient(pathologySample.getSample());
        displayItem.setFirstName(patient.getPerson().getFirstName());
        displayItem.setLastName(patient.getPerson().getLastName());
        displayItem.setLabNumber(pathologySample.getSample().getAccessionNumber());
        displayItem.setPathologySampleId(pathologySample.getId());
        displayItem.setProgramQuestionnaire(fhirUtil.getLocalFhirClient().read().resource(Questionnaire.class)
                .withId(pathologySample.getProgram().getQuestionnaireUUID().toString()).execute());
        displayItem.setProgramQuestionnaireResponse(fhirUtil.getLocalFhirClient().read()
                .resource(QuestionnaireResponse.class).withId(pathologySample.getQuestionnaireResponseUuid().toString())
                .execute());
        return displayItem;
    }

}
