package org.openelisglobal.testcalculated.valueholder;

import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "calculation")
public class Calculation extends BaseObject<Integer> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calculation_generator")
    @SequenceGenerator(name = "calculation_generator", sequenceName = "calculation_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "sample_id")
    private Integer sampleId;
    
    @Column(name = "test_id")
    private Integer testId;

    @Column(name = "result")
    private String result;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "calculation_id", referencedColumnName = "id")
    private List<Operation> operations;

    @Column(name = "toggled")
    private Boolean toggled;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "note")
    private String note;

    @Transient
    String localizedName ;
    
    @Transient
    String stringId ;
    
    @Override
    public Integer getId() {
        return id;
    }
    
    @Override
    public void setId(Integer id) {
        this.id = id ;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getSampleId() {
        return sampleId;
    }
    
    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }
    
    public Integer getTestId() {
        return testId;
    }
    
    public void setTestId(Integer testId) {
        this.testId = testId;
    }
    
    public List<Operation> getOperations() {
        List<Operation> operations = this.operations;
        Collections.sort(operations);
        return operations;
    }
    
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Boolean getToggled() {
        return toggled;
    }

    public void setToggled(Boolean toggled) {
        this.toggled = toggled;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }
    
    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
