package org.openelisglobal.qaevent.valueholder;

import org.openelisglobal.common.valueholder.BaseObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nce_specimen", schema = "clinlims", catalog = "ci_general_9.6")
public class NceSpecimen extends BaseObject<String> {
    private String id;
    private Integer nceId;
    private Integer sampleItemId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nce_id")
    public Integer getNceId() {
        return nceId;
    }

    public void setNceId(Integer nceId) {
        this.nceId = nceId;
    }

    @Basic
    @Column(name = "sample_item_id")
    public Integer getSampleItemId() {
        return sampleItemId;
    }

    public void setSampleItemId(Integer sampleItemId) {
        this.sampleItemId = sampleItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NceSpecimen that = (NceSpecimen) o;
        return id == that.id &&
                Objects.equals(nceId, that.nceId) &&
                Objects.equals(sampleItemId, that.sampleItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nceId, sampleItemId);
    }
}
