package org.openelisglobal.program.valueholder.cytology;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "cytology_diagnosis")
public class CytologyDiagnosis extends BaseObject<Integer> {
    
    public enum DiagnosisCategory {
        
        EPITHELIAL_CELL_ABNORMALITY("Epithelial Cell Abnomality"),
        NON_NEOPLASTIC_CELLULAR_VARIATIONS("Non-neoplastic cellular variations"),
        REACTIVE_CELLULAR_CHANGES("Reactive cellular changes"),
        ORGANISMS("Organisms"),
        OTHER("Other");
        
        private String display;
        
        DiagnosisCategory(String display) {
            this.display = display;
        }
        
        public String getDisplay() {
            return display;
        }
    }
    
    public enum CytologyDiagnosisResultType {
        
        DICTIONARY("D"),
        TEXT("T");
        
        private String code;
        
        CytologyDiagnosisResultType(String code) {
            this.code = code;
        }
        
        public String getCode() {
            return code;
        }
        
        static CytologyDiagnosisResultType fromCode(String code) {
            if (code.equals("D")) {
                return DICTIONARY;
            }
            if (code.equals("T")) {
                return TEXT;
            }
            return null;
        }
    }
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cytology_diagnosis_generator")
    @SequenceGenerator(name = "cytology_diagnosis_generator", sequenceName = "cytology_diagnosis_seq", allocationSize = 1)
    private Integer id;
    
    @Column(name = "negative_diagnosis")
    private Boolean negativeDiagnosis = true;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cytology_diagnosis_id")
    List<CytologyDiagnosisCategoryResultsMap> diagnosisResultsMaps;
    
    @Override
    public Integer getId() {
        return id;
    }
    
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<CytologyDiagnosisCategoryResultsMap> getDiagnosisResultsMaps() {
        return diagnosisResultsMaps;
    }
    
    public void setDiagnosisResultsMaps(List<CytologyDiagnosisCategoryResultsMap> diagnosisResultsMaps) {
        this.diagnosisResultsMaps = diagnosisResultsMaps;
    }
    
    public Boolean getNegativeDiagnosis() {
        return negativeDiagnosis;
    }
    
    public void setNegativeDiagnosis(Boolean negativeDiagnosis) {
        this.negativeDiagnosis = negativeDiagnosis;
    }
    
}
