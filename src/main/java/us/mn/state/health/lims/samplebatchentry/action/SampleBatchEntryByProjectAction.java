package us.mn.state.health.lims.samplebatchentry.action;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.util.Log;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.patient.valueholder.ObservationData;
import us.mn.state.health.lims.sample.action.BaseSampleEntryAction;
import us.mn.state.health.lims.sample.form.ProjectData;

public class SampleBatchEntryByProjectAction extends BaseSampleEntryAction {

	  
	@Override
	protected ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
		String forward = "success";
		BaseActionForm dynaForm = (BaseActionForm) form;
	    String study = request.getParameter("study");
	    try {
		    if ("viralLoad".equals(study)) {
		    	forward = setupViralLoad(dynaForm, request);
		    } else if ("EID".equals(study)) {
		    	forward = setupEID(dynaForm, request);
		    }
		    setupCommonFields(dynaForm, request);	
	    } catch (Exception e) {
	    	Log.error(e.toString());
	    	forward = "fail";
	    }

	    return mapping.findForward(forward);
	}

	private String setupEID(BaseActionForm dynaForm, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ProjectData projectData = (ProjectData) dynaForm.get("ProjectDataEID");
	    PropertyUtils.setProperty(dynaForm, "programCode", StringUtil.getMessageForKey("sample.entry.project.LDBS"));
		String sampleTypes = "";
		String tests = "";
		if (projectData.getDryTubeTaken()) {
			sampleTypes = sampleTypes + StringUtil.getMessageForKey("sample.entry.project.ARV.dryTubeTaken");
		}
		if (projectData.getDbsTaken()) {
			sampleTypes = sampleTypes + " " + StringUtil.getMessageForKey("sample.entry.project.title.dryBloodSpot");
		}
		
		if (projectData.getDnaPCR()) {
			tests = tests + StringUtil.getMessageForKey("sample.entry.project.dnaPCR");
		}
		request.setAttribute("sampleType", sampleTypes);
		request.setAttribute("testNames", tests);
	    PropertyUtils.setProperty(dynaForm, "ProjectData", projectData);
		ObservationData observations = new ObservationData();
	    observations.setProjectFormName("EID_Id");
	    PropertyUtils.setProperty(dynaForm, "observations", observations);
	    
		return findForward(request);
	}

	private String setupViralLoad(BaseActionForm dynaForm, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ProjectData projectData = (ProjectData) dynaForm.get("ProjectDataVL");
	    PropertyUtils.setProperty(dynaForm, "programCode", StringUtil.getMessageForKey("sample.entry.project.LART"));
		String sampleTypes = "";
		String tests = "";
		if (projectData.getDryTubeTaken()) {
			sampleTypes = sampleTypes + StringUtil.getMessageForKey("sample.entry.project.ARV.dryTubeTaken");
		}
		if (projectData.getEdtaTubeTaken()) {
			sampleTypes = sampleTypes + " " + StringUtil.getMessageForKey("sample.entry.project.ARV.edtaTubeTaken");
		}
		
		if (projectData.getViralLoadTest()) {
			tests = tests + StringUtil.getMessageForKey("sample.entry.project.ARV.viralLoadTest");
		}
		request.setAttribute("sampleType", sampleTypes);
		request.setAttribute("testNames", tests);
	    PropertyUtils.setProperty(dynaForm, "ProjectData", projectData);
		ObservationData observations = new ObservationData();
		observations.setProjectFormName("VL_Id");
	    PropertyUtils.setProperty(dynaForm, "observations", observations);

		return findForward(request);
	}
	  
	private void setupCommonFields(BaseActionForm dynaForm, HttpServletRequest request) throws LIMSRuntimeException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(dynaForm, "currentDate", request.getParameter("currentDate"));
	    PropertyUtils.setProperty(dynaForm, "currentTime", request.getParameter("currentTime"));
	    PropertyUtils.setProperty(dynaForm, "receivedDateForDisplay", request.getParameter("sampleOrderItems.receivedDateForDisplay"));
	    PropertyUtils.setProperty(dynaForm, "receivedTimeForDisplay", request.getParameter("sampleOrderItems.receivedTime"));
	    addOrganizationLists(dynaForm);
	}
	
	protected void addOrganizationLists(BaseActionForm dynaForm) throws LIMSRuntimeException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException {

		//Get ARV Centers
		PropertyUtils.setProperty(dynaForm, "ProjectData.ARVCenters", OrganizationTypeList.ARV_ORGS.getList());
		PropertyUtils.setProperty(dynaForm, "organizationTypeLists", OrganizationTypeList.MAP);
		
		//Get EID Sites
		PropertyUtils.setProperty(dynaForm, "ProjectData.EIDSites", OrganizationTypeList.EID_ORGS.getList());
		PropertyUtils.setProperty(dynaForm, "ProjectData.EIDSitesByName", OrganizationTypeList.EID_ORGS_BY_NAME.getList());
		
		//Get EID whichPCR List
		//PropertyUtils.setProperty(dynaForm, "ProjectData.eidWhichPCRList", ObservationHistoryList.EID_WHICH_PCR.getList());
		
		//Get EID secondTestReason List
		PropertyUtils.setProperty(dynaForm, "ProjectData.eidSecondPCRReasonList", ObservationHistoryList.EID_SECOND_PCR_REASON.getList());
		
		//Get SPE Request Reasons
		PropertyUtils.setProperty(dynaForm, "ProjectData.requestReasons", ObservationHistoryList.SPECIAL_REQUEST_REASONS.getList());
		
		PropertyUtils.setProperty(dynaForm, "ProjectData.isUnderInvestigationList", ObservationHistoryList.YES_NO.getList());
	}

	private String findForward(HttpServletRequest request) {
		String method = request.getParameter("method");
		if ("On Demand".equals(method)) {
			return "ondemand";
		} else if ("Pre-Printed".equals(method)) {
			return "preprinted";
		}
		return "fail";
	}
	
	@Override
	protected String getPageTitleKey() {
	    return "sample.batchentry.title";
	}

	  
	@Override
	protected String getPageSubtitleKey() {
	    return "sample.batchentry.title";
	}
}
