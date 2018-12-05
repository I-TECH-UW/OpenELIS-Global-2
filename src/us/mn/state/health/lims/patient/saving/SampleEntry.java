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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.patient.saving;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionErrors;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.provider.query.SampleItemTestProvider;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.sample.form.ProjectData;
import us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper;
import us.mn.state.health.lims.sample.util.CI.BaseProjectFormMapper.TypeOfSampleTests;
import us.mn.state.health.lims.sample.util.CI.ProjectForm;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public class SampleEntry extends Accessioner {
    
    protected DynaBean dynaBean;
    protected HttpServletRequest request;
    
    public SampleEntry(DynaBean dynaBean, String sysUserId, HttpServletRequest request) throws Exception {
        super((String)dynaBean.get("labNo"), (String)dynaBean.get("subjectNumber"), (String)dynaBean.get("siteSubjectNumber"), sysUserId);
        
        this.projectFormMapper = getProjectFormMapper(dynaBean);
        this.projectFormMapper.setPatientForm(false);
        this.projectForm = projectFormMapper.getProjectForm();
        findStatusSet();

        this.dynaBean = dynaBean;
        this.request = request;
        
        this.newPatientStatus = RecordStatus.NotRegistered;
        this.newSampleStatus  = RecordStatus.InitialRegistration;
    }
    
    @Override
    public boolean canAccession() {
        return (statusSet.getPatientRecordStatus() == null && statusSet.getSampleRecordStatus() == null);
    }
    
    @Override
    protected void populateSampleData() throws Exception {
        Timestamp receivedDate = DateUtil.convertStringDateStringTimeToTimestamp(projectFormMapper.getReceivedDate(), projectFormMapper.getReceivedTime());
        Timestamp collectionDate = DateUtil.convertStringDateStringTimeToTimestamp(projectFormMapper.getCollectionDate(), projectFormMapper.getCollectionTime());
        populateSample(receivedDate, collectionDate);
        populateSampleProject();        
        populateSampleOrganization(projectFormMapper.getOrganizationId());
        populateSampleItems();
    }    
    
    protected void populateSampleItems() throws Exception {
        List<TypeOfSampleTests> typeofSampleTestList = projectFormMapper.getTypeOfSampleTests();
        
        boolean testSampleMismatch = false;
        testSampleMismatch = (null == typeofSampleTestList) || (typeofSampleTestList.isEmpty());
        
        if( !testSampleMismatch){
        	for( TypeOfSampleTests typeOfSampleTest : typeofSampleTestList){
        		if( typeOfSampleTest.tests.isEmpty()){
        			testSampleMismatch = true;
        			break;
        		}
        	}
        }
        
        if( testSampleMismatch){
            messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.no.sample"));
            throw new Exception("Mis-match between tests and sample types.");
        }
        
        Timestamp collectionDate = DateUtil.convertStringDateStringTimeToTimestamp(projectFormMapper.getCollectionDate(), projectFormMapper.getCollectionTime());
        populateSampleItems(typeofSampleTestList, collectionDate);
        cleanupSampleItemsAndAnalysis();
    }

    /**
     * Looking at each sampleType which was NOT checked, we pretend that it was checked and see what analysis WOULD be generated
     * combined with any test.
     * If there are any of those, we then try to find some sampleItems to delete and update along with appropriate analysis.
     * @param projectFormMapper
     */
    // TODO PAHill this code relies on the correct spelling of the typeOfSample.description.  These magic names should be
    // moved to one central spot so they are easier to find if needed to change.  They also appear in the ProjectFormMapper, so
    // probably an enum there (BaseProjectFormMapper) could contain the known set for use there and here. 
    
    protected void cleanupSampleItemsAndAnalysis() {
        String sampleId = this.sample.getId();
        if (GenericValidator.isBlankOrNull(sampleId)) {
            return;
        }
        
        // try each type of sample tube and see what real test have gone away.
        ProjectData submittedProjectData = this.projectFormMapper.getProjectData();
        if (!submittedProjectData.getDryTubeTaken()) {
            cleanupSampleAndAnalysis(sampleId, "Dry Tube");
        } else {
            ProjectData tProjectData = buildProjectDataTestsReversed(submittedProjectData) ;
            tProjectData.setDryTubeTaken(true);
            cleanupExistingAnalysis(projectForm, sampleId, tProjectData);
        }
        if (!submittedProjectData.getEdtaTubeTaken()) {
            cleanupSampleAndAnalysis(sampleId, "EDTA Tube");
        } else {
            ProjectData tProjectData = buildProjectDataTestsReversed(submittedProjectData) ;
            tProjectData.setEdtaTubeTaken(true);
            cleanupExistingAnalysis(projectForm, sampleId, tProjectData);
        }
        if (!submittedProjectData.getDbsTaken()) {
            cleanupSampleAndAnalysis(sampleId, "DBS");
        } else {
            ProjectData tProjectData = buildProjectDataTestsReversed(submittedProjectData) ;
            tProjectData.setDbsTaken(true);
            cleanupExistingAnalysis(projectForm, sampleId, tProjectData);
        }
        return;
    }

    /**
     * Check if particular 
     * @param sampleId
     * @param typeName
     */
    private void cleanupSampleAndAnalysis(String sampleId, String typeName) {
        TypeOfSample typeOfSample = BaseProjectFormMapper.getTypeOfSampleByDescription(typeName);
        List<SampleItem> sampleItems = sampleItemDAO.getSampleItemsBySampleIdAndType(sampleId, typeOfSample);
        for (SampleItem sampleItem : sampleItems) {
            List<Analysis> analyses = analysisDAO.getAnalysesBySampleItem(sampleItem);
            if ( cleanupExistingAnalysis(analyses).size() != 0) {
                sampleItem.setSysUserId(sysUserId);
                sampleItemsToDelete.add(sampleItem);
            }
        }
    }

    /**
     *  Find the analysis which are defined to be associated with checked sampleType in the projectdata.
     *  update list of analysisToDelete, analysisToSave
     *  Called with tests checked that are NOT wanted, so we can find those analysis and check to delete them
     * @param projectFormId - the name of form from the UI (typically the unique DIV id)
     * @param sampleId some sample
     * @param tProjectData a mocked up set data of data, representing a set of choices 
     * @return 
     */
    private Map<String, SampleItem> cleanupExistingAnalysis(ProjectForm projectForm, String sampleId, ProjectData tProjectData) {
        try {
            List<Analysis> analysisList = SampleItemTestProvider.findAnalysis(sampleId, projectForm.getProjectFormId(), tProjectData);
            return cleanupExistingAnalysis(analysisList);
        } catch (IllegalArgumentException ignore) {
            return null; // reversing the test boxes resulted in NO valid resuest, so we can move on.
        }
    }

    private Map<String, SampleItem> cleanupExistingAnalysis(List<Analysis> analysisList) {
        String canceledId = StatusService.getInstance().getStatusID(AnalysisStatus.Canceled);
        Map<String, SampleItem> sampleItemsToDelete = new HashMap<String, SampleItem>();
        // first we assume we'll delete them all
        for (Analysis analysis : analysisList) {
            SampleItem sampleItem = analysis.getSampleItem();
            sampleItemsToDelete.put(sampleItem.getId(), sampleItem);
        }
        for (Analysis analysis : analysisList) {
            AnalysisStatus analysisStatus = StatusService.getInstance().getAnalysisStatusForID(analysis.getStatusId());
            switch (analysisStatus) {
            case NotStarted:
                // deletable => leave sampleItem in the Delete list
                analysis.setSysUserId(sysUserId);
                analysisToDelete.add(analysis);
                break;
            case NonConforming_depricated:
                // not deletable => remove the sampleItem from the delete list
                SampleItem sampleItem = analysis.getSampleItem();
                sampleItemsToDelete.remove(sampleItem.getId());
                break;
            default:
                // not deletable => remove the sampleItem from the delete list
                sampleItem = analysis.getSampleItem();
                sampleItemsToDelete.remove(sampleItem.getId());
                // save this analysis for later updating
                analysis.setStatusId(canceledId);
                analysis.setSysUserId(sysUserId);
                analysisToUpdate.add(analysis);
                break;
            }
        }
        return sampleItemsToDelete;
    }

    /**
     * Creates a new projectData object with all Test which are NOT checked in the submitted projectData now checked
     * in the object returned
     * @param ProjectData - nothing but some Test properties are set in this object.
     */
    private ProjectData buildProjectDataTestsReversed(ProjectData submitted) {
        ProjectData projectData = new ProjectData();
        if ( !submitted.getCreatinineTest()   ) projectData.setCreatinineTest(true);
        if ( !submitted.getGlycemiaTest()     ) projectData.setGlycemiaTest(true);
        if ( !submitted.getGenotypingTest()   ) projectData.setGenotypingTest(true);
        if ( !submitted.getNfsTest()          ) projectData.setNfsTest(true);
        if ( !submitted.getSerologyHIVTest()  ) projectData.setSerologyHIVTest(true);
        if ( !submitted.getTransaminaseTest() ) projectData.setTransaminaseTest(true);
        if ( !submitted.getCd4cd8Test()       ) projectData.setCd4cd8Test(true);
        return projectData;
    }
      
    /**
     * @see us.mn.state.health.lims.patient.saving.Accessioner#persistSampleData()
     */
    @Override
    protected void persistSampleData() throws Exception {
        // TODO Auto-generated method stub
        super.persistSampleData();
    }
    
    /**
     * There are no lists in Sample forms, so no repeating OHs to update.
     * If there where the base class returning empty lists for all those without values and then the persist coding deleting
     * all sublists blindly and then rewriting them would be broken, so be careful if it comes to that.
     *  
     * @see us.mn.state.health.lims.patient.saving.Accessioner#persistObservationHistoryLists()
     */
    @Override
    protected void persistObservationHistoryLists() {
        // do nothing.  See note above.  Do not delete this method.
    }

	@Override
	protected String getActionLabel() {
		return StringUtil.getMessageForKey("banner.menu.createSample.Initial");
	}
}
