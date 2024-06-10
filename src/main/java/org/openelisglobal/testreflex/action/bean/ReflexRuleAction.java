package org.openelisglobal.testreflex.action.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reflex_rule_action")
public class ReflexRuleAction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reflex_rule_action_generator")
    @SequenceGenerator(name = "reflex_rule_action_generator", sequenceName = "reflex_rule_action_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "reflex_test_name")
    private String reflexTestName;

    @Column(name = "reflex_test_id")
    private String reflexTestId;

    @Column(name = "sample_id")
    private String sampleId ;

    @Column(name = "internal_note")
    private String internalNote ;

    @Column(name = "external_note")
    private String externalNote;

    @Column(name = "add_notification")
    private String addNotification = "Y" ;

    @Column(name = "test_reflex_id")
    private Integer testReflexId;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleId() {
        return sampleId;
    }
    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }
    public String getReflexTestName() {
        return reflexTestName;
    }
    public void setReflexTestName(String reflexTestName) {
        this.reflexTestName = reflexTestName;
    }
    public String getReflexTestId() {
        return reflexTestId;
    }
    public void setReflexTestId(String reflexTestId) {
        this.reflexTestId = reflexTestId;
    }
    public String getInternalNote() {
        return internalNote;
    }
    public void setInternalNote(String internalNote) {
        this.internalNote = internalNote;
    }
    public String getExternalNote() {
        return externalNote;
    }
    public void setExternalNote(String externalNote) {
        this.externalNote = externalNote;
    }
    public String getAddNotification() {
        return addNotification;
    }
    public void setAddNotification(String addNotification) {
        this.addNotification = addNotification;
    }
    
    public Integer getTestReflexId() {
        return testReflexId;
    }
    
    public void setTestReflexId(Integer testReflexId) {
        this.testReflexId = testReflexId;
    }
}
