package org.openelisglobal.testreflex.action.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reflex_rule_condition")
public class ReflexRuleCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reflex_rule_condition_generator")
    @SequenceGenerator(name = "reflex_rule_condition_generator", sequenceName = "reflex_rule_condition_seq", allocationSize = 1)
    @Column(name = "id")
    private String id;

    @Column(name = "sample")
    private String sample;

    @Column(name = "test")
    private String test;

    @Column(name = "test_id")
    private String testId;

    @Column(name = "relation")
    private String relation;

    @Column(name = "value")
    private String value ;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSample() {
        return sample;
    }
    public void setSample(String sample) {
        this.sample = sample;
    }
    public String getTest() {
        return test;
    }
    public void setTest(String test) {
        this.test = test;
    }
    public String getTestId() {
        return testId;
    }
    public void setTestId(String testId) {
        this.testId = testId;
    }
    public String getRelation() {
        return relation;
    }
    public void setRelation(String relation) {
        this.relation = relation;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
