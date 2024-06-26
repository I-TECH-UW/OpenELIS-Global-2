package org.openelisglobal.reports.action.implementation.reportBeans;

public class WHONETColumnBuilder {

    private String firstName = "";
    private String lastName = "";
    private String gender = "";
    private String SpecimenType = "";
    private String SpecimenDate = "";
    private String Organism = "";
    private String Antibiotic = "";
    private String Result = "";

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecimenType() {
        return SpecimenType;
    }

    public void setSpecimenType(String specimenType) {
        SpecimenType = specimenType;
    }

    public String getSpecimenDate() {
        return SpecimenDate;
    }

    public void setSpecimenDate(String specimenDate) {
        SpecimenDate = specimenDate;
    }

    public String getOrganism() {
        return Organism;
    }

    public void setOrganism(String organism) {
        Organism = organism;
    }

    public String getAntibiotic() {
        return Antibiotic;
    }

    public void setAntibiotic(String antibiotic) {
        Antibiotic = antibiotic;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}
