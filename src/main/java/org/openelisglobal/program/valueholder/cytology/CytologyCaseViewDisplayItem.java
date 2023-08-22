package org.openelisglobal.program.valueholder.cytology;

import java.util.List;

import org.hl7.fhir.r4.model.Questionnaire;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.program.valueholder.cytology.CytologySpecimenAdequacy.SpecimenAdequancySatisfaction;

public class CytologyCaseViewDisplayItem extends CytologyDisplayItem {
    
    private String age;
    
    private String sex;
    
    private String referringFacility;
    
    private String department;
    
    private String requester;
    
    private Questionnaire programQuestionnaire;
    
    private QuestionnaireResponse programQuestionnaireResponse;
    
    private String assignedTechnicianId;
    
    private String assignedPathologistId;
    
    private List<CytologySlide> slides;
    
    private List<IdValuePair> adequacies;
    
    private SpecimenAdequancySatisfaction satisfaction;
    
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getReferringFacility() {
        return referringFacility;
    }
    
    public void setReferringFacility(String referringFacility) {
        this.referringFacility = referringFacility;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getRequester() {
        return requester;
    }
    
    public void setRequester(String requester) {
        this.requester = requester;
    }
    
    public Questionnaire getProgramQuestionnaire() {
        return programQuestionnaire;
    }
    
    public void setProgramQuestionnaire(Questionnaire programQuestionnaire) {
        this.programQuestionnaire = programQuestionnaire;
    }
    
    public QuestionnaireResponse getProgramQuestionnaireResponse() {
        return programQuestionnaireResponse;
    }
    
    public void setProgramQuestionnaireResponse(QuestionnaireResponse programQuestionnaireResponse) {
        this.programQuestionnaireResponse = programQuestionnaireResponse;
    }
    
    public String getAssignedTechnicianId() {
        return assignedTechnicianId;
    }
    
    public void setAssignedTechnicianId(String assignedTechnicianId) {
        this.assignedTechnicianId = assignedTechnicianId;
    }
    
    public String getAssignedPathologistId() {
        return assignedPathologistId;
    }
    
    public void setAssignedPathologistId(String assignedPathologistId) {
        this.assignedPathologistId = assignedPathologistId;
    }
    
    public List<CytologySlide> getSlides() {
        return slides;
    }
    
    public void setSlides(List<CytologySlide> slides) {
        this.slides = slides;
    }
    
    public SpecimenAdequancySatisfaction getSatisfaction() {
        return satisfaction;
    }
    
    public void setSatisfaction(SpecimenAdequancySatisfaction satisfaction) {
        this.satisfaction = satisfaction;
    }
    
    public List<IdValuePair> getAdequacies() {
        return adequacies;
    }
    
    public void setAdequacies(List<IdValuePair> adequacies) {
        this.adequacies = adequacies;
    }
    
}
