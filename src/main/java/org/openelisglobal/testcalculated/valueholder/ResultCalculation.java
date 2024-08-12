package org.openelisglobal.testcalculated.valueholder;

import java.util.Map;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.openelisglobal.common.valueholder.BaseObject;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.result.valueholder.Result;
import org.openelisglobal.test.valueholder.Test;

@Entity
@Table(name = "result_calculation")
public class ResultCalculation extends BaseObject<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_calculation_generator")
    @SequenceGenerator(name = "result_calculation_generator", sequenceName = "result_calculation_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "calculation_id")
    private Calculation calculation;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "result_id")
    private Result result;

    @ElementCollection
    @CollectionTable(name = "test_operations", joinColumns = @JoinColumn(name = "result_calculation_id", referencedColumnName = "id"))
    @Column(name = "test_id")
    private Set<Test> test;

    @ElementCollection
    @CollectionTable(name = "test_result_map", joinColumns = @JoinColumn(name = "result_calculation_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "test_id")
    @Column(name = "result_id")
    private Map<Integer, Integer> testResultMap;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Test> getTest() {
        return test;
    }

    public void setTest(Set<Test> test) {
        this.test = test;
    }

    public Map<Integer, Integer> getTestResultMap() {
        return testResultMap;
    }

    public void setTestResultMap(Map<Integer, Integer> testResultMap) {
        this.testResultMap = testResultMap;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
