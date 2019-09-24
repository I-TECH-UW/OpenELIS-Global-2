package org.openelisglobal.qaevent.valueholder;

import org.openelisglobal.common.valueholder.BaseObject;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "nce_action_log", schema = "clinlims", catalog = "ci_general_9.6")
public class NceActionLog extends BaseObject<String> {

    private String id;
    private String correctiveAction;
    private String actionType;
    private String personResponsible;
    private Date dateCompleted;
    private Integer turnAroundTime;
    private Integer ncEventId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "corrective_action")
    public String getCorrectiveAction() {
        return correctiveAction;
    }

    public void setCorrectiveAction(String correctiveAction) {
        this.correctiveAction = correctiveAction;
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
    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
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
    public Integer getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(Integer turnAoundTime) {
        this.turnAroundTime = turnAoundTime;
    }

    @Basic
    @Column(name = "nc_event_id")
    public Integer getNcEventId() {
        return ncEventId;
    }

    public void setNcEventId(Integer ncEventId) {
        this.ncEventId = ncEventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NceActionLog that = (NceActionLog) o;
        return id == that.id &&
                Objects.equals(correctiveAction, that.correctiveAction) &&
                Objects.equals(actionType, that.actionType) &&
                Objects.equals(personResponsible, that.personResponsible) &&
                Objects.equals(dateCompleted, that.dateCompleted) &&
                Objects.equals(turnAroundTime, that.turnAroundTime) &&
                Objects.equals(ncEventId, that.ncEventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, correctiveAction, actionType, personResponsible, dateCompleted, turnAroundTime, ncEventId);
    }
}
