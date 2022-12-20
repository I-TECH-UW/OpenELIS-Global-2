package org.openelisglobal.testreflex.action.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reflex_rule")
public class ReflexRule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reflex_rule_generator")
    @SequenceGenerator(name = "reflex_rule_generator", sequenceName = "reflex_rule_seq", allocationSize = 1)
    @Column(name = "id")
    private String id;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "overall")
    private String overall;

    @Column(name = "toggled")
    private Boolean toggled;

    @OneToMany(cascade = CascadeType.ALL)  
    @JoinColumn(name = "reflex_rule_id", referencedColumnName = "id")   
    List<ReflexRuleCondition> conditions;

    @OneToMany(cascade = CascadeType.ALL)  
    @JoinColumn(name = "reflex_rule_id", referencedColumnName = "id") 
    List<ReflexRuleAction> actions;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getOverall() {
        return overall;
    }
    public void setOverall(String overall) {
        this.overall = overall;
    }
    public Boolean getToggled() {
        return toggled;
    }
    public void setToggled(Boolean toggled) {
        this.toggled = toggled;
    }
    public List<ReflexRuleCondition> getConditions() {
        return conditions;
    }
    public void setConditions(List<ReflexRuleCondition> conditions) {
        this.conditions = conditions;
    }
    public List<ReflexRuleAction> getActions() {
        return actions;
    }
    public void setActions(List<ReflexRuleAction> actions) {
        this.actions = actions;
    }

}
