package org.openelisglobal.program.valueholder.pathology;

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
@Table(name = "pathology_sample")
public class PathologySample extends ProgramSample {
    
    public enum PathologyStatus {
        
        GROSSING("Grossing"),
        CUTTING("Cutting"),
        PROCESSING("Processing"),
        SLICING("Slicing for Slides"),
        STAINING("Staining"),
        READY_PATHOLOGIST("Ready for Pathologist"),
        ADDITIONAL_REQUEST("Additional Pathologist Request"),
        COMPLETED("Completed");
        
        private String display;
        
        PathologyStatus(String display) {
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
    private PathologyStatus status = PathologyStatus.GROSSING;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pathology_sample_id")
    private List<PathologyBlock> blocks;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pathology_sample_id")
    private List<PathologySlide> slides;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pathology_sample_id")
    private List<PathologyRequest> requests;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pathology_sample_id")
    private List<PathologyTechnique> techniques;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pathology_sample_id")
    private List<PathologyConclusion> conclusions;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pathology_sample_id")
    private List<PathologyReport> reports;
    
    @Column(name = "gross_exam")
    private String grossExam;
    
    @Column(name = "microscopy_exam")
    private String microscopyExam;
    
    public List<PathologyRequest> getRequests() {
        return requests;
    }
    
    public void setRequests(List<PathologyRequest> requests) {
        this.requests = requests;
    }
    
    public List<PathologyTechnique> getTechniques() {
        return techniques;
    }
    
    public void setTechniques(List<PathologyTechnique> techniques) {
        this.techniques = techniques;
    }
    
    public List<PathologyConclusion> getConclusions() {
        return conclusions;
    }
    
    public void setConclusions(List<PathologyConclusion> conclusions) {
        this.conclusions = conclusions;
    }
    
    public PathologyStatus getStatus() {
        return status;
    }
    
    public void setStatus(PathologyStatus status) {
        this.status = status;
    }
    
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
    
    public List<PathologyBlock> getBlocks() {
        return blocks;
    }
    
    public void setBlocks(List<PathologyBlock> blocks) {
        this.blocks = blocks;
    }
    
    public List<PathologySlide> getSlides() {
        return slides;
    }
    
    public void setSlides(List<PathologySlide> slides) {
        this.slides = slides;
    }
    
    public String getGrossExam() {
        return grossExam;
    }
    
    public void setGrossExam(String grossExam) {
        this.grossExam = grossExam;
    }
    
    public String getMicroscopyExam() {
        return microscopyExam;
    }
    
    public void setMicroscopyExam(String microscopyExam) {
        this.microscopyExam = microscopyExam;
    }
    
    public List<PathologyReport> getReports() {
        return reports;
    }
    
    public void setReports(List<PathologyReport> reports) {
        this.reports = reports;
    }
    
}
