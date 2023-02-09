package org.openelisglobal.testreflex.action.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "reflex_rule")
public class ReflexRule extends BaseObject<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reflex_rule_generator")
    @SequenceGenerator(name = "reflex_rule_generator", sequenceName = "reflex_rule_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "rule_name")
    private String ruleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "overall")
    private ReflexRuleOtions.OverallOptions overall ;

    @Column(name = "toggled")
    private Boolean toggled;

    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)  
    @JoinColumn(name = "reflex_rule_id", referencedColumnName = "id")   
    Set<ReflexRuleCondition> conditions;

    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)  
    @JoinColumn(name = "reflex_rule_id", referencedColumnName = "id") 
    Set<ReflexRuleAction> actions;

    @Override
    public Integer getId() {
        return id;
    }
    
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public ReflexRuleOtions.OverallOptions getOverall() {
        return overall;
    }
    public void setOverall(ReflexRuleOtions.OverallOptions overall) {
        this.overall = overall;
    }
    public Boolean getToggled() {
        return toggled;
    }
    public void setToggled(Boolean toggled) {
        this.toggled = toggled;
    }
    public Set<ReflexRuleCondition> getConditions() {
        return conditions;
    }
    public void setConditions(Set<ReflexRuleCondition> conditions) {
        this.conditions = conditions;
    }
    public Set<ReflexRuleAction> getActions() {
        return actions;
    }
    public void setActions(Set<ReflexRuleAction> actions) {
        this.actions = actions;
    }

}
