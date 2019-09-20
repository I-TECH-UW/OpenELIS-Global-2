package org.openelisglobal.qaevent.valueholder;

import org.openelisglobal.common.valueholder.BaseObject;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "lab_component", schema = "clinlims", catalog = "ci_general_9.6")
public class LabComponent extends BaseObject<String> {
    private String id;
    private String name;
    private Timestamp lastmodified;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastmodified")
    public Timestamp getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Timestamp lastmodified) {
        this.lastmodified = lastmodified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabComponent that = (LabComponent) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastmodified, that.lastmodified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastmodified);
    }
}
