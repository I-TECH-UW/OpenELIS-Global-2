package org.openelisglobal.qaevent.valueholder;

import org.openelisglobal.common.valueholder.BaseObject;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "nce_action_log", schema = "clinlims", catalog = "ci_general_9.6")
public class NceActionLog extends BaseObject<String> {

    private String id;
    private String description;
    private String actionType;
    private Integer personResponsible;
    private Date dateCompleted;
    private Integer turnAoundTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "action_type")
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Basic
    @Column(name = "person_responsible")
    public Integer getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(Integer personResponsible) {
        this.personResponsible = personResponsible;
    }

    @Basic
    @Column(name = "date_completed")
    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Basic
    @Column(name = "turn_aound_time")
    public Integer getTurnAoundTime() {
        return turnAoundTime;
    }

    public void setTurnAoundTime(Integer turnAoundTime) {
        this.turnAoundTime = turnAoundTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NceActionLog that = (NceActionLog) o;
        return id == that.id &&
                Objects.equals(description, that.description) &&
                Objects.equals(actionType, that.actionType) &&
                Objects.equals(personResponsible, that.personResponsible) &&
                Objects.equals(dateCompleted, that.dateCompleted) &&
                Objects.equals(turnAoundTime, that.turnAoundTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, actionType, personResponsible, dateCompleted, turnAoundTime);
    }
}
