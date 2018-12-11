package us.mn.state.health.lims.sample.action;


import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.saving.Accessioner;
import us.mn.state.health.lims.sample.util.CI.ProjectForm;


/**
 * The SampleEntryByProjectAction class represents the initial Action for the SampleEntry
 * form of the application for the Retro-CI workflow
 *
 */
public class SampleEntryByProjectAction extends BaseSampleEntryAction {

	protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		request.getSession().setAttribute(IActionConstants.SAVE_DISABLED, IActionConstants.TRUE);

		BaseActionForm dynaForm = (BaseActionForm) form;

        // retrieve the current project, before clearing, so that we can set it on later.
        String projectFormId = Accessioner.findProjectFormName(dynaForm);
        // Initialize the form.
        dynaForm.initialize(mapping);
        updateRequestType(request);

		// Set received date and entered date to today's date
		Date today = Calendar.getInstance().getTime();
		String dateAsText = DateUtil.formatDateAsText(today );
		PropertyUtils.setProperty(form, "currentDate", dateAsText);
		PropertyUtils.setProperty(dynaForm, "receivedDateForDisplay", dateAsText);
		PropertyUtils.setProperty(dynaForm, "interviewDate", dateAsText);
		PropertyUtils.setProperty(dynaForm, "labNo", "");
		
		SampleOrderService sampleOrderService = new SampleOrderService();
	    PropertyUtils.setProperty( dynaForm, "sampleOrderItems", sampleOrderService.getSampleOrderItem() );
		PropertyUtils.setProperty(dynaForm, "patientProperties", new PatientManagementInfo());
	    PropertyUtils.setProperty( dynaForm, "patientSearch", new PatientSearch() );
		PropertyUtils.setProperty(dynaForm, "sampleTypes", DisplayListService.getList(ListType.SAMPLE_TYPE_ACTIVE));
		PropertyUtils.setProperty(dynaForm, "testSectionList", DisplayListService.getList(ListType.TEST_SECTION));


		addGenderList(dynaForm);
		addProjectList(dynaForm);

		addOrganizationLists(dynaForm);
		addAllPatientFormLists(dynaForm);

        setProjectFormName(form, projectFormId);
        
        if (FormFields.getInstance().useField(FormFields.Field.InitialSampleCondition)) {
			PropertyUtils.setProperty(dynaForm, "initialSampleConditionList", DisplayListService.getList(ListType.INITIAL_SAMPLE_CONDITION));
		}

        String forward = findForward(projectFormId);
		return mapping.findForward(forward);
	}


	/**
	 * One form ID at this time is actually on another JSP page, someday maybe others
     * @param projectFormId
     * @return
     */
    private String findForward(String projectFormId) {
        ProjectForm projectForm = ProjectForm.findProjectFormByFormId(projectFormId);
        if (projectForm == null) {
            return FWD_SUCCESS;
        }
        switch (projectForm) {
        case EID:
            return FWD_EID_ENTRY;
        case VL:
            return FWD_VL_ENTRY;
        default:
            return FWD_SUCCESS;
        }
    }

    protected String getPageSubtitleKey() {
		String key = null;

		switch (requestType) {
			case INITIAL: {
				key = "banner.menu.createSample.Initial";
				break;
			}
			case VERIFY: {
				key = "banner.menu.createSample.Verify";
				break;
			}

			default: {
				key = "banner.menu.sampleCreate";
			}
		}

		return key;
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
}
