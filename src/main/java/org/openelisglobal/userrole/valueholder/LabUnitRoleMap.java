package org.openelisglobal.userrole.valueholder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * LabUnitRoleMap represents a Map of Map<String , List<String>> ie <labUnit ,
 * List<roles>>
 */
@Entity
@Table(name = "lab_unit_role_map")
public class LabUnitRoleMap {

    @Id
    @GeneratedValue
    @Column(name = "lab_unit_role_map_id")
    private Integer id;

    @Column(name = "lab_unit")
    private String labUnit;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "lab_roles", joinColumns = {
            @JoinColumn(name = "lab_unit_role_map_id", referencedColumnName = "lab_unit_role_map_id") })
    @Column(name = "role")
    Set<String> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabUnit() {
        return labUnit;
    }

    public void setLabUnit(String labUnit) {
        this.labUnit = labUnit;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = new HashSet<>(roles);
    }
}
