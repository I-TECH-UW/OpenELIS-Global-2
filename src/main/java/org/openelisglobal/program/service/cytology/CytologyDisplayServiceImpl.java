package org.openelisglobal.program.service.cytology;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Questionnaire;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.openelisglobal.common.services.SampleOrderService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.dictionary.service.DictionaryService;
import org.openelisglobal.organization.service.OrganizationService;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.program.valueholder.cytology.CytologyCaseViewDisplayItem;
import org.openelisglobal.program.valueholder.cytology.CytologyDisplayItem;
import org.openelisglobal.program.valueholder.cytology.CytologySample;
import org.openelisglobal.sample.bean.SampleOrderItem;
import org.openelisglobal.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CytologyDisplayServiceImpl implements CytologyDisplayService{


    @Autowired
    private SampleService sampleService;
    @Autowired
    private CytologySampleService cytologySampleService;
    @Autowired
    private FhirUtil fhirUtil;
    @Autowired
    private OrganizationService organizationService;

    @Override
    public CytologyCaseViewDisplayItem convertToCaseDisplayItem(Integer cytologySampleId) {
        CytologySample cytologySample = cytologySampleService.get(cytologySampleId);
        CytologyCaseViewDisplayItem displayItem = new CytologyCaseViewDisplayItem();
        displayItem.setStatus(cytologySample.getStatus());
        displayItem.setRequestDate(cytologySample.getSample().getEnteredDate());
        if (cytologySample.getCytoPathologist() != null) {
            displayItem.setAssignedCytoPathologist(cytologySample.getCytoPathologist().getDisplayName());
        }
        if (cytologySample.getTechnician() != null) {
            displayItem.setAssignedTechnician(cytologySample.getTechnician().getDisplayName());
        }
        Patient patient = sampleService.getPatient(cytologySample.getSample());
        displayItem.setFirstName(patient.getPerson().getFirstName());
        displayItem.setLastName(patient.getPerson().getLastName());
        displayItem.setLabNumber(cytologySample.getSample().getAccessionNumber());
        displayItem.setPathologySampleId(cytologySample.getId());
         displayItem.setProgramQuestionnaire(fhirUtil.getLocalFhirClient().read().resource(Questionnaire.class)
                .withId(cytologySample.getProgram().getQuestionnaireUUID().toString()).execute());
        displayItem.setProgramQuestionnaireResponse(fhirUtil.getLocalFhirClient().read()
                .resource(QuestionnaireResponse.class).withId(cytologySample.getQuestionnaireResponseUuid().toString())
                .execute());
        
        cytologySample.getSlides().size();
        displayItem.setSlides(cytologySample.getSlides()); 
        displayItem.setAdequacy(cytologySample.getSpecimenAdequacy());       
        SampleOrderService sampleOrderService = new SampleOrderService(cytologySample.getSample());
        SampleOrderItem sampleItem = sampleOrderService.getSampleOrderItem();
        displayItem.setReferringFacility(sampleItem.getReferringSiteName()); 
        if (StringUtils.isNotBlank(sampleItem.getReferringSiteDepartmentId())) {
            Organization org = organizationService.get(sampleItem.getReferringSiteDepartmentId());
            if (org != null) {
                displayItem.setDepartment(org.getLocalizedName());
            }
        } 
        displayItem.setRequester(sampleItem.getProviderLastName() +" "+ sampleItem.getProviderFirstName());                  
        displayItem.setAge(DateUtil.getCurrentAgeForDate(patient.getBirthDate() ,DateUtil.getNowAsTimestamp()));        
        return displayItem; 
    }

    @Override
    public CytologyDisplayItem convertToDisplayItem(Integer cytologySampleId) {
        CytologySample cytologySample = cytologySampleService.get(cytologySampleId);
        CytologyDisplayItem displayItem = new CytologyDisplayItem();
        displayItem.setStatus(cytologySample.getStatus());
        displayItem.setRequestDate(cytologySample.getSample().getEnteredDate());
        if (cytologySample.getCytoPathologist() != null) {
            displayItem.setAssignedCytoPathologist(cytologySample.getCytoPathologist().getDisplayName());
        }
        if (cytologySample.getTechnician() != null) {
            displayItem.setAssignedTechnician(cytologySample.getTechnician().getDisplayName());
        }
        Patient patient = sampleService.getPatient(cytologySample.getSample());
        displayItem.setFirstName(patient.getPerson().getFirstName());
        displayItem.setLastName(patient.getPerson().getLastName());
        displayItem.setLabNumber(cytologySample.getSample().getAccessionNumber());
        displayItem.setPathologySampleId(cytologySample.getId());
        return displayItem;
    }
    
}
