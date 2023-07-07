package org.openelisglobal.testcalculated.valueholder;

import java.util.Set;

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
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "calculation_id", referencedColumnName = "id")
    private Set<Operation> operations;
    
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
    
    public Set<Operation> getOperations() {
        return operations;
    }
    
    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }
    
}
