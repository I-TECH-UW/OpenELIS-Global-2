package spring.mine.sample.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.sample.form.SampleEditForm;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.DisplayListService.ListType;
import us.mn.state.health.lims.common.services.IPatientService;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.services.SampleOrderService;
import us.mn.state.health.lims.common.services.SampleService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.SampleStatus;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.login.dao.UserModuleDAO;
import us.mn.state.health.lims.login.daoimpl.UserModuleDAOImpl;
import us.mn.state.health.lims.patient.action.bean.PatientSearch;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.bean.SampleEditItem;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleTestDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleTestDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;

@Controller
public class SampleEditController extends BaseController {

	private static final TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
	private static final AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private static final UserModuleDAO userModuleDAO = new UserModuleDAOImpl();
	private static final SampleEditItemComparator testComparator = new SampleEditItemComparator();
	private static final Set<Integer> excludedAnalysisStatusList;
	private static final Set<Integer> ENTERED_STATUS_SAMPLE_LIST = new HashSet<>();
	private static final Collection<String> ABLE_TO_CANCEL_ROLE_NAMES = new ArrayList<>();

	static {
		excludedAnalysisStatusList = new HashSet<>();
		excludedAnalysisStatusList
				.add(Integer.parseInt(StatusService.getInstance().getStatusID(AnalysisStatus.Canceled)));

		ENTERED_STATUS_SAMPLE_LIST.add(Integer.parseInt(StatusService.getInstance().getStatusID(SampleStatus.Entered)));
		ABLE_TO_CANCEL_ROLE_NAMES.add("Validator");
		ABLE_TO_CANCEL_ROLE_NAMES.add("Validation");
		ABLE_TO_CANCEL_ROLE_NAMES.add("Biologist");
	}

	@RequestMapping(value = "/SampleEdit", method = RequestMethod.GET)
	public ModelAndView showSampleEdit(HttpServletRequest request, @ModelAttribute("form") SampleEditForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new SampleEditForm();
		}
		form.setFormAction("SampleEdit.do");
		Errors errors = new BaseErrors();
		

		request.getSession().setAttribute(SAVE_DISABLED, TRUE);

		String accessionNumber = request.getParameter("accessionNumber");
		boolean allowedToCancelResults = userModuleDAO.isUserAdmin(request)
				|| new UserRoleDAOImpl().userInRole(getSysUserId(request), ABLE_TO_CANCEL_ROLE_NAMES);

		if (GenericValidator.isBlankOrNull(accessionNumber)) {
			accessionNumber = getMostRecentAccessionNumberForPaitient(request.getParameter("patientID"));
		}

		boolean isEditable = "readwrite"
				.equals(request.getSession().getAttribute(IActionConstants.SAMPLE_EDIT_WRITABLE))
				|| "readwrite".equals(request.getParameter("type"));
		PropertyUtils.setProperty(form, "isEditable", isEditable);
		if (!GenericValidator.isBlankOrNull(accessionNumber)) {
			PropertyUtils.setProperty(form, "accessionNumber", accessionNumber);
			PropertyUtils.setProperty(form, "searchFinished", Boolean.TRUE);

			Sample sample = getSample(accessionNumber);

			if (sample != null && !GenericValidator.isBlankOrNull(sample.getId())) {

				List<SampleItem> sampleItemList = getSampleItems(sample);
				setPatientInfo(form, sample);
				List<SampleEditItem> currentTestList = getCurrentTestInfo(sampleItemList, accessionNumber,
						allowedToCancelResults);
				PropertyUtils.setProperty(form, "existingTests", currentTestList);
				setAddableTestInfo(form, sampleItemList, accessionNumber);
				setAddableSampleTypes(form);
				setSampleOrderInfo(form, sample);
				PropertyUtils.setProperty(form, "ableToCancelResults",
						hasResults(currentTestList, allowedToCancelResults));
				String maxAccessionNumber = accessionNumber + "-"
						+ sampleItemList.get(sampleItemList.size() - 1).getSortOrder();
				PropertyUtils.setProperty(form, "maxAccessionNumber", maxAccessionNumber);
				PropertyUtils.setProperty(form, "isConfirmationSample",
						new SampleService(sample).isConfirmationSample());
			} else {
				PropertyUtils.setProperty(form, "noSampleFound", Boolean.TRUE);
			}
		} else {
			PropertyUtils.setProperty(form, "searchFinished", Boolean.FALSE);
			request.getSession().setAttribute(IActionConstants.SAMPLE_EDIT_WRITABLE, request.getParameter("type"));
		}

		if (FormFields.getInstance().useField(FormFields.Field.InitialSampleCondition)) {
			PropertyUtils.setProperty(form, "initialSampleConditionList",
					DisplayListService.getList(ListType.INITIAL_SAMPLE_CONDITION));
		}

		PropertyUtils.setProperty(form, "currentDate", DateUtil.getCurrentDateAsText());
		PatientSearch patientSearch = new PatientSearch();
		patientSearch.setLoadFromServerWithPatient(true);
		patientSearch.setSelectedPatientActionButtonText(StringUtil.getMessageForKey("label.patient.search.select"));
		PropertyUtils.setProperty(form, "patientSearch", patientSearch);
		PropertyUtils.setProperty(form, "warning", "show");

		return findForward(forward, form);
	}

	private Boolean hasResults(List<SampleEditItem> currentTestList, boolean allowedToCancelResults) {
		if (!allowedToCancelResults) {
			return false;
		}

		for (SampleEditItem editItem : currentTestList) {
			if (editItem.isHasResults()) {
				return true;
			}
		}

		return false;
	}

	private void setSampleOrderInfo(SampleEditForm form, Sample sample)
			throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
		SampleOrderService sampleOrderService = new SampleOrderService(sample);
		PropertyUtils.setProperty(form, "sampleOrderItems", sampleOrderService.getSampleOrderItem());
	}

	private String getMostRecentAccessionNumberForPaitient(String patientID) {
		String accessionNumber = null;
		if (!GenericValidator.isBlankOrNull(patientID)) {
			List<Sample> samples = new SampleHumanDAOImpl().getSamplesForPatient(patientID);

			int maxId = 0;
			for (Sample sample : samples) {
				if (Integer.parseInt(sample.getId()) > maxId) {
					maxId = Integer.parseInt(sample.getId());
					accessionNumber = sample.getAccessionNumber();
				}
			}

		}
		return accessionNumber;
	}

	private Sample getSample(String accessionNumber) {
		SampleDAO sampleDAO = new SampleDAOImpl();
		return sampleDAO.getSampleByAccessionNumber(accessionNumber);
	}

	private List<SampleItem> getSampleItems(Sample sample) {
		SampleItemDAO sampleItemDAO = new SampleItemDAOImpl();

		return sampleItemDAO.getSampleItemsBySampleIdAndStatus(sample.getId(), ENTERED_STATUS_SAMPLE_LIST);
	}

	private void setPatientInfo(SampleEditForm form, Sample sample)
			throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

		Patient patient = new SampleHumanDAOImpl().getPatientForSample(sample);
		IPatientService patientService = new PatientService(patient);

		PropertyUtils.setProperty(form, "patientName", patientService.getLastFirstName());
		PropertyUtils.setProperty(form, "dob", patientService.getEnteredDOB());
		PropertyUtils.setProperty(form, "gender", patientService.getGender());
		PropertyUtils.setProperty(form, "nationalId", patientService.getNationalId());
	}

	private List<SampleEditItem> getCurrentTestInfo(List<SampleItem> sampleItemList, String accessionNumber,
			boolean allowedToCancelAll) {
		List<SampleEditItem> currentTestList = new ArrayList<>();

		for (SampleItem sampleItem : sampleItemList) {
			addCurrentTestsToList(sampleItem, currentTestList, accessionNumber, allowedToCancelAll);
		}

		return currentTestList;
	}

	private void addCurrentTestsToList(SampleItem sampleItem, List<SampleEditItem> currentTestList,
			String accessionNumber, boolean allowedToCancelAll) {

		TypeOfSample typeOfSample = new TypeOfSample();
		typeOfSample.setId(sampleItem.getTypeOfSampleId());
		typeOfSampleDAO.getData(typeOfSample);

		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleItemsExcludingByStatusIds(sampleItem,
				excludedAnalysisStatusList);

		List<SampleEditItem> analysisSampleItemList = new ArrayList<>();

		String collectionDate = DateUtil.convertTimestampToStringDate(sampleItem.getCollectionDate());
		String collectionTime = DateUtil.convertTimestampToStringTime(sampleItem.getCollectionDate());
		boolean canRemove = true;
		for (Analysis analysis : analysisList) {
			SampleEditItem sampleEditItem = new SampleEditItem();

			sampleEditItem.setTestId(analysis.getTest().getId());
			sampleEditItem.setTestName(TestService.getUserLocalizedTestName(analysis.getTest()));
			sampleEditItem.setSampleItemId(sampleItem.getId());

			boolean canCancel = allowedToCancelAll
					|| (!StatusService.getInstance().matches(analysis.getStatusId(), AnalysisStatus.Canceled)
							&& StatusService.getInstance().matches(analysis.getStatusId(), AnalysisStatus.NotStarted));

			if (!canCancel) {
				canRemove = false;
			}
			sampleEditItem.setCanCancel(canCancel);
			sampleEditItem.setAnalysisId(analysis.getId());
			sampleEditItem.setStatus(StatusService.getInstance().getStatusNameFromId(analysis.getStatusId()));
			sampleEditItem.setSortOrder(analysis.getTest().getSortOrder());
			sampleEditItem.setHasResults(
					!StatusService.getInstance().matches(analysis.getStatusId(), AnalysisStatus.NotStarted));

			analysisSampleItemList.add(sampleEditItem);
		}

		if (!analysisSampleItemList.isEmpty()) {
			Collections.sort(analysisSampleItemList, testComparator);
			SampleEditItem firstItem = analysisSampleItemList.get(0);

			firstItem.setAccessionNumber(accessionNumber + "-" + sampleItem.getSortOrder());
			firstItem.setSampleType(typeOfSample.getLocalizedName());
			firstItem.setCanRemoveSample(canRemove);
			firstItem.setCollectionDate(collectionDate == null ? "" : collectionDate);
			firstItem.setCollectionTime(collectionTime);
			currentTestList.addAll(analysisSampleItemList);
		}
	}

	private void setAddableTestInfo(SampleEditForm form, List<SampleItem> sampleItemList, String accessionNumber)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<SampleEditItem> possibleTestList = new ArrayList<>();

		for (SampleItem sampleItem : sampleItemList) {
			addPossibleTestsToList(sampleItem, possibleTestList, accessionNumber);
		}

		PropertyUtils.setProperty(form, "possibleTests", possibleTestList);
		PropertyUtils.setProperty(form, "testSectionList", DisplayListService.getList(ListType.TEST_SECTION));
	}

	private void setAddableSampleTypes(SampleEditForm form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(form, "sampleTypes", DisplayListService.getList(ListType.SAMPLE_TYPE_ACTIVE));
	}

	private void addPossibleTestsToList(SampleItem sampleItem, List<SampleEditItem> possibleTestList,
			String accessionNumber) {

		TypeOfSample typeOfSample = new TypeOfSample();
		typeOfSample.setId(sampleItem.getTypeOfSampleId());
		typeOfSampleDAO.getData(typeOfSample);

		TestDAO testDAO = new TestDAOImpl();
		Test test = new Test();

		TypeOfSampleTestDAO sampleTypeTestDAO = new TypeOfSampleTestDAOImpl();
		List<TypeOfSampleTest> typeOfSampleTestList = sampleTypeTestDAO
				.getTypeOfSampleTestsForSampleType(typeOfSample.getId());
		List<SampleEditItem> typeOfTestSampleItemList = new ArrayList<>();

		for (TypeOfSampleTest typeOfSampleTest : typeOfSampleTestList) {
			SampleEditItem sampleEditItem = new SampleEditItem();

			sampleEditItem.setTestId(typeOfSampleTest.getTestId());
			test.setId(typeOfSampleTest.getTestId());
			testDAO.getData(test);
			if ("Y".equals(test.getIsActive()) && test.getOrderable()) {
				sampleEditItem.setTestName(TestService.getUserLocalizedTestName(test));
				sampleEditItem.setSampleItemId(sampleItem.getId());
				sampleEditItem.setSortOrder(test.getSortOrder());
				typeOfTestSampleItemList.add(sampleEditItem);
			}
		}

		if (!typeOfTestSampleItemList.isEmpty()) {
			Collections.sort(typeOfTestSampleItemList, testComparator);

			typeOfTestSampleItemList.get(0).setAccessionNumber(accessionNumber + "-" + sampleItem.getSortOrder());
			typeOfTestSampleItemList.get(0).setSampleType(typeOfSample.getLocalizedName());

			possibleTestList.addAll(typeOfTestSampleItemList);
		}

	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if ("success".equals(forward)) {
			return new ModelAndView("sampleEditDefinition", "form", form);
		} else if ("fail".equals(forward)) {
			return new ModelAndView("homePageDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		boolean isEditable = "readwrite"
				.equals(request.getSession().getAttribute(IActionConstants.SAMPLE_EDIT_WRITABLE))
				|| "readwrite".equals(request.getParameter("type"));
		return isEditable ? StringUtil.getContextualKeyForKey("sample.edit.title")
				: StringUtil.getContextualKeyForKey("sample.view.title");
	}

	@Override
	protected String getPageSubtitleKey() {
		boolean isEditable = "readwrite"
				.equals(request.getSession().getAttribute(IActionConstants.SAMPLE_EDIT_WRITABLE))
				|| "readwrite".equals(request.getParameter("type"));
		return isEditable ? StringUtil.getContextualKeyForKey("sample.edit.subtitle")
				: StringUtil.getContextualKeyForKey("sample.view.subtitle");
	}

	private static class SampleEditItemComparator implements Comparator<SampleEditItem> {

		@Override
		public int compare(SampleEditItem o1, SampleEditItem o2) {
			if (GenericValidator.isBlankOrNull(o1.getSortOrder())
					|| GenericValidator.isBlankOrNull(o2.getSortOrder())) {
				return o1.getTestName().compareTo(o2.getTestName());
			}

			try {
				return Integer.parseInt(o1.getSortOrder()) - Integer.parseInt(o2.getSortOrder());
			} catch (NumberFormatException e) {
				return o1.getTestName().compareTo(o2.getTestName());
			}
		}

	}
}
