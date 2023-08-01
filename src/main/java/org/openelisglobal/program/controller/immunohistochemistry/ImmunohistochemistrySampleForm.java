package org.openelisglobal.program.controller.immunohistochemistry;

import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistrySample.ImmunohistochemistryStatus;

public class ImmunohistochemistrySampleForm {

    private ImmunohistochemistryStatus status;

    private String assignedPathologistId;
    private String assignedTechnicianId;

    private String systemUserId;

    private Boolean release;

    public ImmunohistochemistryStatus getStatus() {
        return status;
    }

    public void setStatus(ImmunohistochemistryStatus status) {
        this.status = status;
    }

    public String getAssignedPathologistId() {
        return assignedPathologistId;
    }

    public void setAssignedPathologistId(String assignedPathologistId) {
        this.assignedPathologistId = assignedPathologistId;
    }

    public String getAssignedTechnicianId() {
        return assignedTechnicianId;
    }

    public void setAssignedTechnicianId(String assignedTechnicianId) {
        this.assignedTechnicianId = assignedTechnicianId;
    }

    public Boolean getRelease() {
        return release;
    }

    public void setRelease(Boolean release) {
        this.release = release;
    }

    public String getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

}
