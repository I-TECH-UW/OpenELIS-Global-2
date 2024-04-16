package org.openelisglobal.program.valueholder.cytology;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.openelisglobal.program.valueholder.ProgramSample;
import org.openelisglobal.systemuser.valueholder.SystemUser;

@Entity
@Table(name = "cytology_sample")
public class CytologySample extends ProgramSample {
    
    public enum CytologyStatus {
        
        PREPARING_SLIDES("Preparing slides"),
        SCREENING("Screening"),
        READY_FOR_CYTOPATHOLOGIST("Ready for Cytopathologist"),
        COMPLETED("Completed");
        
        private String display;
        
        CytologyStatus(String display) {
            this.display = display;
        }
        
        public String getDisplay() {
            return display;
        }
    }
    
    @Valid
    @OneToOne
    @JoinColumn(name = "technician_id", referencedColumnName = "id")
    private SystemUser technician;
    
    @Valid
    @OneToOne
    @JoinColumn(name = "cytopathologist_id", referencedColumnName = "id")
    private SystemUser cytoPathologist;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    private CytologyStatus status = CytologyStatus.PREPARING_SLIDES;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cytology_sample_id")
    private List<CytologySlide> slides;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "specimen_adequacy_id", referencedColumnName = "id")
    private CytologySpecimenAdequacy specimenAdequacy;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cytology_diagnosis_id", referencedColumnName = "id")
    private CytologyDiagnosis diagnosis;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cytology_sample_id")
    private List<CytologyReport> reports;
    
    public SystemUser getTechnician() {
        return technician;
    }
    
    public void setTechnician(SystemUser technician) {
        this.technician = technician;
    }
    
    public CytologyStatus getStatus() {
        return status;
    }
    
    public void setStatus(CytologyStatus status) {
        this.status = status;
    }
    
    public List<CytologySlide> getSlides() {
        return slides;
    }
    
    public void setSlides(List<CytologySlide> slides) {
        this.slides = slides;
    }
    
    public SystemUser getCytoPathologist() {
        return cytoPathologist;
    }
    
    public void setCytoPathologist(SystemUser cytoPathologist) {
        this.cytoPathologist = cytoPathologist;
    }
    
    public CytologySpecimenAdequacy getSpecimenAdequacy() {
        return specimenAdequacy;
    }
    
    public void setSpecimenAdequacy(CytologySpecimenAdequacy specimenAdequacy) {
        this.specimenAdequacy = specimenAdequacy;
    }
    
    public CytologyDiagnosis getDiagnosis() {
        return diagnosis;
    }
    
    public void setDiagnosis(CytologyDiagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public List<CytologyReport> getReports() {
        return reports;
    }
    
    public void setReports(List<CytologyReport> reports) {
        this.reports = reports;
    }
    
}
