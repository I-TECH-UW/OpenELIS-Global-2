package org.openelisglobal.qaevent.valueholder;

import org.openelisglobal.common.valueholder.BaseObject;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "nce_type", schema = "clinlims", catalog = "ci_general_9.6")
public class NceType extends BaseObject<String> {
    private String id;
    private String name;
    private String displayKey;
    private String active;
    private Timestamp lastupdated;

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
    @Column(name = "display_key")
    public String getDisplayKey() {
        return displayKey;
    }

    public void setDisplayKey(String displayKey) {
        this.displayKey = displayKey;
    }

    @Basic
    @Column(name = "active")
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Basic
    @Column(name = "lastupdated")
    public Timestamp getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Timestamp lastupdated) {
        this.lastupdated = lastupdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NceType nceType = (NceType) o;
        return id == nceType.id &&
                Objects.equals(name, nceType.name) &&
                Objects.equals(displayKey, nceType.displayKey) &&
                Objects.equals(active, nceType.active) &&
                Objects.equals(lastupdated, nceType.lastupdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, displayKey, active, lastupdated);
    }
}
