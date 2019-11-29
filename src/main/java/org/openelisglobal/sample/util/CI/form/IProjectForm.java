package org.openelisglobal.sample.util.CI.form;

import org.openelisglobal.patient.valueholder.ObservationData;
import org.openelisglobal.sample.form.ProjectData;

public interface IProjectForm {

    ProjectData getProjectData();

    void setProjectData(ProjectData projectData);

    ObservationData getObservations();

    void setObservations(ObservationData observationData);

    String getBirthDateForDisplay();

    void setBirthDateForDisplay(String birthDateForDisplay);

    String getSiteSubjectNumber();

    void setSiteSubjectNumber(String siteSubjectNumber);

    String getSubjectNumber();

    void setSubjectNumber(String subjectNumber);
}
