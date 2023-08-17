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
        
        INCOMPLETE("Incomplete Only"),
        PREPARING_SLIDES("Preparing slides"),
        SCREENING("Screening"),
        CYTOPATHOLOGIST_REVIEW("Cytopathologist Review"),
        COMPLETED("Completed"),
        READY_FOR_SCREEMER("Ready for screener"),
        READY_FOR_CYTOPATHOLOGIST("Ready for Cytopathologist");
        
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
    @JoinColumn(name = "pathologist_id", referencedColumnName = "id")
    private SystemUser pathologist;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    private CytologyStatus status = CytologyStatus.PREPARING_SLIDES;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cytology_sample_id")
    private List<CytologySlide> slides;

    
    public SystemUser getTechnician() {
        return technician;
    }

    
    public void setTechnician(SystemUser technician) {
        this.technician = technician;
    }

    
    public SystemUser getPathologist() {
        return pathologist;
    }

    
    public void setPathologist(SystemUser pathologist) {
        this.pathologist = pathologist;
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
    

    
}
