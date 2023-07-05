package org.openelisglobal.testcalculated.valueholder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "calculation")
public class Calculation  extends BaseObject<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calculation_generator")
    @SequenceGenerator(name = "calculation_generator", sequenceName = "calculation_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    private Integer sampleId ;

    private Integer testId ;
    
    private String testName;

    @Override
    public Integer getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    @Override
    public void setId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
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

    
    public String getTestName() {
        return testName;
    }

    
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
}
