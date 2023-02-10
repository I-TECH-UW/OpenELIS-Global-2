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

    @Column(name = "action")
    private String action;

    @Column(name = "reflex_result")
    private String reflexResult;

    @Column(name = "reflex_result_test_id")
    private String reflexResultTestId;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getReflexResult() {
        return reflexResult;
    }
    public void setReflexResult(String reflexResult) {
        this.reflexResult = reflexResult;
    }
    public String getReflexResultTestId() {
        return reflexResultTestId;
    }
    public void setReflexResultTestId(String reflexResultTestId) {
        this.reflexResultTestId = reflexResultTestId;
    }
}
