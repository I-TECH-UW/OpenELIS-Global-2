package org.openelisglobal.program.valueholder;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.openelisglobal.common.valueholder.BaseObject;
import org.openelisglobal.sample.valueholder.Sample;

@Entity
@Table(name = "program_sample")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ProgramSample extends BaseObject<Integer> {

    private static final long serialVersionUID = -979624722823577192L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_sample_generator")
    @SequenceGenerator(name = "program_sample_generator", sequenceName = "program_sample_seq", allocationSize = 1)
    private Integer id;

    @Valid
    @NotNull
    @OneToOne
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    private Program program;

    @Valid
    @NotNull
    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "questionnaire_response_uuid")
    private UUID questionnaireResponseUuid;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public UUID getQuestionnaireResponseUuid() {
        return questionnaireResponseUuid;
    }

    public void setQuestionnaireResponseUuid(UUID questionnaireResponseUuid) {
        this.questionnaireResponseUuid = questionnaireResponseUuid;
    }
}
