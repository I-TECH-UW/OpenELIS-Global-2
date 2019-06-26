package spring.mine.sample.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.form.SamplePatientEntryForm;
import spring.mine.sample.validator.SamplePatientEntryFormValidator;
import spring.service.sample.PatientManagementUpdate;
import spring.service.sample.SamplePatientEntryService;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.patient.action.IPatientUpdate;
import us.mn.state.health.lims.patient.action.IPatientUpdate.PatientUpdateStatus;
import us.mn.state.health.lims.patient.action.bean.PatientManagementInfo;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.sample.action.util.SamplePatientUpdateData;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;

@Controller
public class SamplePatientEntryController extends BaseSampleEntryController {

	@Autowired
	SamplePatientEntryFormValidator formValidator;

	@Autowired
	private SamplePatientEntryService samplePatientService;
	@Autowired
	PatientManagementUpdate patientUpdate;

	@RequestMapping(value = "/SamplePatientEntry", method = RequestMethod.GET)
	public ModelAndView showSamplePatientEntry(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		SamplePatientEntryForm form = new SamplePatientEntryForm();

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);
		SampleOrderService sampleOrderService = new SampleOrderService();
		PropertyUtils.setProperty(form, "sampleOrderItems", sampleOrderService.getSampleOrderItem());
		PropertyUtils.setProperty(form, "patientProperties", new PatientManagementInfo());
		PropertyUtils.setProperty(form, "patientSearch", new PatientSearch());
		PropertyUtils.setProperty(form, "sampleTypes",
				DisplayListService.getInstance().getList(ListType.SAMPLE_TYPE_ACTIVE));
		PropertyUtils.setProperty(form, "testSectionList",
				DisplayListService.getInstance().getList(ListType.TEST_SECTION));
		PropertyUtils.setProperty(form, "currentDate", DateUtil.getCurrentDateAsText());

		// for (Object program : form.getSampleOrderItems().getProgramList()) {
		// System.out.println(((IdValuePair) program).getValue());
		// }

		addProjectList(form);
		addBillingLabel();

		if (FormFields.getInstance().useField(FormFields.Field.InitialSampleCondition)) {
			PropertyUtils.setProperty(form, "initialSampleConditionList",
					DisplayListService.getInstance().getList(ListType.INITIAL_SAMPLE_CONDITION));
		}

		addFlashMsgsToRequest(request);
		return findForward(FWD_SUCCESS, form);
	}

	@RequestMapping(value = "/SamplePatientEntry", method = RequestMethod.POST)
	public @ResponseBody ModelAndView showSamplePatientEntrySave(HttpServletRequest request,
			@ModelAttribute("form") @Validated(SamplePatientEntryForm.SamplePatientEntry.class) SamplePatientEntryForm form,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			return findForward(FWD_FAIL_INSERT, form);
		}
		SamplePatientUpdateData updateData = new SamplePatientUpdateData(getSysUserId(request));

		PatientManagementInfo patientInfo = (PatientManagementInfo) PropertyUtils.getProperty(form,
				"patientProperties");
		SampleOrderItem sampleOrder = (SampleOrderItem) PropertyUtils.getProperty(form, "sampleOrderItems");

		boolean trackPayments = ConfigurationProperties.getInstance()
				.isPropertyValueEqual(Property.TRACK_PATIENT_PAYMENT, "true");

		String receivedDateForDisplay = sampleOrder.getReceivedDateForDisplay();

		if (!GenericValidator.isBlankOrNull(sampleOrder.getReceivedTime())) {
			receivedDateForDisplay += " " + sampleOrder.getReceivedTime();
		} else {
			receivedDateForDisplay += " 00:00";
		}

		updateData.setCollectionDateFromRecieveDateIfNeeded(receivedDateForDisplay);
		updateData.initializeRequester(sampleOrder);

		patientUpdate.setSysUserIdFromRequest(request);
		testAndInitializePatientForSaving(request, patientInfo, patientUpdate, updateData);

		updateData.setAccessionNumber(sampleOrder.getLabNo());
		updateData.initProvider(sampleOrder);
		updateData.initSampleData(form.getSampleXML(), receivedDateForDisplay, trackPayments, sampleOrder);
		updateData.validateSample(result);

		if (result.hasErrors()) {
			saveErrors(result);
			// setSuccessFlag(request, true);
			return findForward(FWD_FAIL_INSERT, form);
		}

		try {
			samplePatientService.persistData(updateData, patientUpdate, patientInfo, form, request);
		} catch (LIMSRuntimeException lre) {
			// ActionError error;
			if (lre.getException() instanceof StaleObjectStateException) {
				// error = new ActionError("errors.OptimisticLockException", null, null);
				result.reject("errors.OptimisticLockException", "errors.OptimisticLockException");
			} else {
				lre.printStackTrace();
				// error = new ActionError("errors.UpdateException", null, null);
				result.reject("errors.UpdateException", "errors.UpdateException");
			}
			System.out.println(result);

			// errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(result);
			request.setAttribute(ALLOW_EDITS_KEY, "false");
			return findForward(FWD_FAIL_INSERT, form);

		}

		redirectAttributes.addFlashAttribute(FWD_SUCCESS, true);
		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private void testAndInitializePatientForSaving(HttpServletRequest request, PatientManagementInfo patientInfo,
			IPatientUpdate patientUpdate, SamplePatientUpdateData updateData)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		patientUpdate.setPatientUpdateStatus(patientInfo);
		updateData.setSavePatient(patientUpdate.getPatientUpdateStatus() != PatientUpdateStatus.NO_ACTION);

		if (updateData.isSavePatient()) {
			updateData.setPatientErrors(patientUpdate.preparePatientData(request, patientInfo));
		} else {
			updateData.setPatientErrors(new BaseErrors());
		}
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "samplePatientEntryDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "homePageDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/SamplePatientEntry.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "samplePatientEntryDefinition";
		} else {
			return "PageNotFound";
		}
	}
}
