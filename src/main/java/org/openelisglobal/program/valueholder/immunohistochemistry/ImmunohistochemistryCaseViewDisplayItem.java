package org.openelisglobal.program.valueholder.immunohistochemistry;

import java.util.List;

import org.hl7.fhir.r4.model.Questionnaire;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class ImmunohistochemistryCaseViewDisplayItem extends ImmunohistochemistryDisplayItem {

    private String age;
    private String sex;
    private String referringFacility;
    private Questionnaire programQuestionnaire;
    private QuestionnaireResponse programQuestionnaireResponse;
    private String assignedTechnicianId;
    private String assignedPathologistId;
    private List<ImmunohistochemistrySampleReport> reports;

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

    public String getAssignedPathologistId() {
        return assignedPathologistId;
    }

    public void setAssignedPathologistId(String assignedPathologistId) {
        this.assignedPathologistId = assignedPathologistId;
    }

    public String getAssignedTechnicianId() {
        return assignedTechnicianId;
    }

    public void setAssignedTechnicianId(String assignedTechnicianId) {
        this.assignedTechnicianId = assignedTechnicianId;
    }
 
    public List<ImmunohistochemistrySampleReport> getReports() {
        return reports;
    }

    public void setReports(List<ImmunohistochemistrySampleReport> reports) {
        this.reports = reports;
    }
}
