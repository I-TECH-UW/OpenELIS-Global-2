/*
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/

/**
 * C�te d'Ivoire
 * @author pahill
 * @since 2010-06-15
 */
package org.openelisglobal.patient.saving;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.patient.form.PatientEntryByProjectForm;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.services.StatusService.RecordStatus;
import org.openelisglobal.common.util.DateUtil;

@Service
@Scope("prototype")
@Primary
public class PatientEntry extends Accessioner implements IPatientEntry {

    protected HttpServletRequest request;

    public PatientEntry(PatientEntryByProjectForm form, String sysUserId, HttpServletRequest request)
            throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        this();
        setFieldsFromForm(form);
        setRequest(request);
        setSysUserId(sysUserId);
    }

    public PatientEntry() {
        super();
        newPatientStatus = RecordStatus.InitialRegistration;
        newSampleStatus = RecordStatus.NotRegistered;
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setFieldsFromForm(PatientEntryByProjectForm form)
            throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        setAccessionNumber((String) form.get("labNo"));
        setPatientSiteSubjectNo((String) form.get("siteSubjectNumber"));
        setPatientIdentifier((String) form.get("subjectNumber"));
        setProjectFormMapperFromForm(form);
    }

    public void setProjectFormMapperFromForm(PatientEntryByProjectForm form)
            throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        projectFormMapper = getProjectFormMapper(form);
        projectFormMapper.setPatientForm(true);
        projectForm = projectFormMapper.getProjectForm();
        findStatusSet();

    }

    @Override
    public boolean canAccession() {
        return (statusSet.getPatientRecordStatus() == null && statusSet.getSampleRecordStatus() == null);
    }

    @Override
    protected void populateSampleData() throws Exception {
        Timestamp receivedDate = DateUtil.convertStringDatePreserveStringTimeToTimestamp(
                sample.getReceivedDateForDisplay(), sample.getReceived24HourTimeForDisplay(),
                projectFormMapper.getReceivedDate(), projectFormMapper.getReceivedTime());
        Timestamp collectionDate = DateUtil.convertStringDatePreserveStringTimeToTimestamp(
                sample.getCollectionDateForDisplay(), sample.getCollectionTimeForDisplay(),
                projectFormMapper.getCollectionDate(), projectFormMapper.getCollectionTime());

        populateSample(receivedDate, collectionDate);
        populateSampleProject();
        populateSampleOrganization(projectFormMapper.getOrganizationId());
    }

    @Override
    protected String getActionLabel() {
        return MessageUtil.getMessage("banner.menu.createPatient.Initial");
    }
}
