package org.openelisglobal.program.valueholder.pathology;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "pathology_conclusion")
public class PathologyConclusion extends BaseObject<Integer> {

    public enum ConclusionType {
        DICTIONARY("D"), TEXT("T");

        private String code;

        ConclusionType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        static ConclusionType fromCode(String code) {
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pathology_conclusion_generator")
    @SequenceGenerator(name = "pathology_conclusion_generator", sequenceName = "pathology_conclusion_seq", allocationSize = 1)
    private Integer id;

    private String value;

    @Enumerated(EnumType.STRING)
    private ConclusionType type;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ConclusionType getType() {
        return type;
    }

    public void setType(ConclusionType type) {
        this.type = type;
    }
}
