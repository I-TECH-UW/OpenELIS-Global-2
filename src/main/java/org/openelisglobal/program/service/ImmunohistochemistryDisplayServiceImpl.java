package org.openelisglobal.program.service;

import javax.transaction.Transactional;

import org.hl7.fhir.r4.model.Questionnaire;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistryCaseViewDisplayItem;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistryDisplayItem;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistrySample;
import org.openelisglobal.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImmunohistochemistryDisplayServiceImpl implements ImmunohistochemistryDisplayService {

    @Autowired
    private SampleService sampleService;
    @Autowired
    private ImmunohistochemistrySampleService immunohistochemistrySampleService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private FhirUtil fhirUtil;

    @Override
    @Transactional
    public ImmunohistochemistryDisplayItem convertToDisplayItem(Integer immunohistochemistrySampleId) {
        ImmunohistochemistrySample immunohistochemistrySample = immunohistochemistrySampleService
                .get(immunohistochemistrySampleId);
        ImmunohistochemistryDisplayItem displayItem = new ImmunohistochemistryDisplayItem();
        displayItem.setStatus(immunohistochemistrySample.getStatus());
        displayItem.setRequestDate(immunohistochemistrySample.getSample().getEnteredDate());
        if (immunohistochemistrySample.getPathologist() != null) {
            displayItem.setAssignedPathologist(immunohistochemistrySample.getPathologist().getDisplayName());
        }
        if (immunohistochemistrySample.getTechnician() != null) {
            displayItem.setAssignedTechnician(immunohistochemistrySample.getTechnician().getDisplayName());
        }
        Patient patient = sampleService.getPatient(immunohistochemistrySample.getSample());
        displayItem.setFirstName(patient.getPerson().getFirstName());
        displayItem.setLastName(patient.getPerson().getLastName());
        displayItem.setLabNumber(immunohistochemistrySample.getSample().getAccessionNumber());
        displayItem.setImmunohistochemistrySampleId(immunohistochemistrySample.getId());
        return displayItem;
    }

    @Override
    @Transactional
    public ImmunohistochemistryCaseViewDisplayItem convertToCaseDisplayItem(Integer immunohistochemistrySampleId) {
        ImmunohistochemistrySample immunohistochemistrySample = immunohistochemistrySampleService
                .get(immunohistochemistrySampleId);
        ImmunohistochemistryCaseViewDisplayItem displayItem = new ImmunohistochemistryCaseViewDisplayItem();
        displayItem.setStatus(immunohistochemistrySample.getStatus());
        displayItem.setRequestDate(immunohistochemistrySample.getSample().getEnteredDate());
        if (immunohistochemistrySample.getPathologist() != null) {
            displayItem.setAssignedPathologist(immunohistochemistrySample.getPathologist().getDisplayName());
            displayItem.setAssignedPathologistId(immunohistochemistrySample.getPathologist().getId());
        }
        if (immunohistochemistrySample.getTechnician() != null) {
            displayItem.setAssignedTechnician(immunohistochemistrySample.getTechnician().getDisplayName());
            displayItem.setAssignedTechnicianId(immunohistochemistrySample.getTechnician().getId());
        }
        Patient patient = sampleService.getPatient(immunohistochemistrySample.getSample());
        displayItem.setFirstName(patient.getPerson().getFirstName());
        displayItem.setLastName(patient.getPerson().getLastName());
        displayItem.setLabNumber(immunohistochemistrySample.getSample().getAccessionNumber());
        displayItem.setImmunohistochemistrySampleId(immunohistochemistrySample.getId());
        displayItem.setProgramQuestionnaire(fhirUtil.getLocalFhirClient().read().resource(Questionnaire.class)
                .withId(immunohistochemistrySample.getProgram().getQuestionnaireUUID().toString()).execute());
        displayItem.setProgramQuestionnaireResponse(
                fhirUtil.getLocalFhirClient().read().resource(QuestionnaireResponse.class)
                        .withId(immunohistochemistrySample.getQuestionnaireResponseUuid().toString()).execute());

        return displayItem;
    }

}
