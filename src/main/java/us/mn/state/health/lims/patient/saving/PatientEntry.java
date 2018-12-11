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
package us.mn.state.health.lims.patient.saving;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;


public class PatientEntry extends Accessioner {

    protected HttpServletRequest request;

    public PatientEntry(DynaBean dynaBean, String sysUserId, HttpServletRequest request) throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        super((String)dynaBean.get("labNo"), (String)dynaBean.get("subjectNumber"), (String)dynaBean.get("siteSubjectNumber"), sysUserId);
        
        this.projectFormMapper = getProjectFormMapper(dynaBean);
        this.projectFormMapper.setPatientForm(true);
        this.projectForm = projectFormMapper.getProjectForm();
        findStatusSet();
        
        this.request = request;
        
        this.newPatientStatus = RecordStatus.InitialRegistration;
        this.newSampleStatus  = RecordStatus.NotRegistered;
    }

    @Override
    public boolean canAccession() {
        return (statusSet.getPatientRecordStatus() == null && statusSet.getSampleRecordStatus() == null);
    }



    @Override
    protected void populateSampleData() throws Exception {
    	Timestamp receivedDate = DateUtil.convertStringDatePreserveStringTimeToTimestamp(sample.getReceivedDateForDisplay(), sample.getReceived24HourTimeForDisplay( ),
    																					 projectFormMapper.getReceivedDate(), projectFormMapper.getReceivedTime());
    	Timestamp collectionDate = DateUtil.convertStringDatePreserveStringTimeToTimestamp(sample.getCollectionDateForDisplay(), sample.getCollectionTimeForDisplay(),
    																					   projectFormMapper.getCollectionDate(), projectFormMapper.getCollectionTime());
        
        populateSample(receivedDate, collectionDate);
        populateSampleProject();
        populateSampleOrganization(projectFormMapper.getOrganizationId());
    }

	@Override
	protected String getActionLabel() {
		return StringUtil.getMessageForKey("banner.menu.createPatient.Initial");
	}   
}
