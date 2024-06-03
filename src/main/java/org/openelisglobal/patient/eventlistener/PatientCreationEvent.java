package org.openelisglobal.patient.eventlistener;

import lombok.Getter;
import org.openelisglobal.patient.action.bean.PatientManagementInfo;
import org.springframework.context.ApplicationEvent;

/**
 * Carries the patient information as a message when a patient is created.
 */
@Getter
public class PatientCreationEvent extends ApplicationEvent {
    private final PatientManagementInfo patientInfo;

    public PatientCreationEvent(Object source, PatientManagementInfo patientInfo) {
        super(source);
        this.patientInfo = patientInfo;
    }

}

