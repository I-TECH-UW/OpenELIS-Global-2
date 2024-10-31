package org.openelisglobal.program.valueholder.cytology;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "cytology_specimen_adequacy")
public class CytologySpecimenAdequacy extends BaseObject<Integer> {

    public enum SpecimenAdequacyResultType {
        DICTIONARY("D"), TEXT("T");

        private String code;

        SpecimenAdequacyResultType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        static SpecimenAdequacyResultType fromCode(String code) {
            if (code.equals("D")) {
                return DICTIONARY;
            }
            if (code.equals("T")) {
                return TEXT;
            }
            return null;
        }
    }

    public enum SpecimenAdequancySatisfaction {
        SATISFACTORY_FOR_EVALUATION("Satisfactory for evaluation"),
        UN_SATISFACTORY_FOR_EVALUATION("Un Satisfactory for evaluation");

        private String display;

        SpecimenAdequancySatisfaction(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return display;
        }
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cytology_specimen_adequacy_generator")
    @SequenceGenerator(name = "cytology_specimen_adequacy_generator", sequenceName = "cytology_specimen_adequacy_seq", allocationSize = 1)
    private Integer id;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "cytology_specimen_adequacy_value", joinColumns = @JoinColumn(name = "cytology_specimen_adequacy_id"))
    @Column(name = "value", nullable = false)
    private List<String> values;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_type")
    private SpecimenAdequacyResultType resultType = SpecimenAdequacyResultType.DICTIONARY;

    @Enumerated(EnumType.STRING)
    @Column(name = "satisfaction")
    private SpecimenAdequancySatisfaction satisfaction;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public SpecimenAdequacyResultType getResultType() {
        return resultType;
    }

    public void setResultType(SpecimenAdequacyResultType resultType) {
        this.resultType = resultType;
    }

    public SpecimenAdequancySatisfaction getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(SpecimenAdequancySatisfaction satisfaction) {
        this.satisfaction = satisfaction;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
