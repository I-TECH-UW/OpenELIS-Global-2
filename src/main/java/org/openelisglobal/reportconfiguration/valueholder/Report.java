package org.openelisglobal.reportconfiguration.valueholder;

import org.openelisglobal.common.valueholder.BaseObject;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Report extends BaseObject<String> {
    private String id;
    private String name;
    private String type;
    private String category;
    private Integer sortOrder;
    private Boolean visible;
    private String menuElementId;
    private String displayKey;

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 200)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "category", nullable = true, length = 200)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "sort_order", nullable = true)
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Basic
    @Column(name = "visible", nullable = true)
    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Basic
    @Column(name = "menu_element_id", nullable = true, length = 100)
    public String getMenuElementId() {
        return menuElementId;
    }

    public void setMenuElementId(String menuElementId) {
        this.menuElementId = menuElementId;
    }

    @Basic
    @Column(name = "display_key", nullable = true, length = 200)
    public String getDisplayKey() {
        return displayKey;
    }

    public void setDisplayKey(String displayKey) {
        this.displayKey = displayKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id &&
                Objects.equals(type, report.type) &&
                Objects.equals(category, report.category) &&
                Objects.equals(sortOrder, report.sortOrder) &&
                Objects.equals(visible, report.visible) &&
                Objects.equals(menuElementId, report.menuElementId) &&
                Objects.equals(displayKey, report.displayKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, category, sortOrder, visible, menuElementId, displayKey);
    }
}
