/**
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


package us.mn.state.health.lims.sample.util.CI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;

import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.valueholder.ObservationData;
import us.mn.state.health.lims.sample.form.ProjectData;
import us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper.TypeOfSampleTests;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

/**
 * Provide information about mapping from the *entryByProject JSP forms and appropriate data structures
 * @author Laura Nixon, Paul A. Hill (pahill@uw.edu)
 * @since June 20, 2010
 */
public interface IProjectFormMapper {
	
	/**
	 * Find all of the appropriate tests which have been requested
	 * @return list of Type Of Sample and the tests requested OR null if the right sample type was not selected.
	 */
    public ArrayList<TypeOfSampleTests> getTypeOfSampleTests();
	public String getProjectCode();
	public ProjectForm getProjectForm();
	public String getOrganizationId();
	
	public String getSiteSubjectNumber();
	
	/**
	 * @return a list of observation histories which occur on the form once.
	 */
	public List<ObservationHistory>readObservationHistories(ObservationData od);
	/**
	 * A Map of lists of multi-valued observation history entities, so that even if a list is 100% empty, the key tells the caller what type was supposed to be in the list
	 * (so the caller can clean up old existing entities of this type from the DB.
	 * @return map<ObservationHistoryTypeId, List<ObservationHistory>>
	 */
	public Map<String, List<ObservationHistory>> readObservationHistoryLists(ObservationData od);
	
	/**
	 * Sometimes we want to push things into the patient record (and its children), but sometimes we don't.
	 * Override this to change that behavior.
	 * @return
	 */
	public boolean getShouldPopulatePatient();
    /**
     * Second Entry is a CI (RetroCI) procedure where data is always entered twice and verified to be the same.
     * @param request
     * @return
     */
    public boolean isSecondEntry(HttpServletRequest request);
    /**
     * @return the collection date from the form data.
     */
    public String getCollectionDate();
    /**
     * @return the collection date from the form data.
     */
    public String getCollectionTime();
    
    /**
     * @return the known DB id if any for the patient.
     */
    public String getPatientId();
    
    /**
     * @return the known DB id if any for the patient.
     */
    public String getSampleId();
    /**
     * @return the received date from the form data.
     */
    public String getReceivedDate();
    /**
     * @return the received time from the form data.
     */
    public String getReceivedTime();
    /**
     * @param b  are we working with a patient form or a sample form?
     */
    public void setPatientForm(boolean b);
        
    public void setProjectData(ProjectData projectData);
    
    public TypeOfSample getTypeOfSample(String typeName);
    
    public ProjectData getProjectData();
    /**
     * 
     */
    public DynaBean getDynaBean();    
}