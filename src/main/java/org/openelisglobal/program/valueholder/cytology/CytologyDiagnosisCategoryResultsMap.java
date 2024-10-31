package org.openelisglobal.program.valueholder.cytology;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.openelisglobal.common.hibernateConverter.StringListConverter;
import org.openelisglobal.common.valueholder.BaseObject;
import org.openelisglobal.program.valueholder.cytology.CytologyDiagnosis.CytologyDiagnosisResultType;
import org.openelisglobal.program.valueholder.cytology.CytologyDiagnosis.DiagnosisCategory;

@Entity
@Table(name = "cytology_diagnosis_result_map")
public class CytologyDiagnosisCategoryResultsMap extends BaseObject<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cytology_diagnosis_result_map_generator")
    @SequenceGenerator(name = "cytology_diagnosis_result_map_generator", sequenceName = "cytology_diagnosis_result_map_seq", allocationSize = 1)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private DiagnosisCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_type")
    private CytologyDiagnosisResultType resultType;

    @Convert(converter = StringListConverter.class)
    @Column(name = "results")
    private List<String> results;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    public CytologyDiagnosisResultType getResultType() {
        return resultType;
    }

    public void setResultType(CytologyDiagnosisResultType resultType) {
        this.resultType = resultType;
    }

    public DiagnosisCategory getCategory() {
        return category;
    }

    public void setCategory(DiagnosisCategory category) {
        this.category = category;
    }
}
