package spring.mine.test.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.internationalization.MessageUtil;
import spring.mine.test.form.BatchTestReassignment;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.AnalysisService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.action.BatchTestStatusChangeBean;
import us.mn.state.health.lims.test.valueholder.Test;

@Controller
public class BatchTestReassignmentUpdateController extends BaseController {

	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();

	@RequestMapping(value = "/BatchTestReassignmentUpdate", method = RequestMethod.POST)
	public ModelAndView showBatchTestReassignmentUpdate(HttpServletRequest request,
			@ModelAttribute("form") BatchTestReassignment form)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new BatchTestReassignment();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		
		String currentUserId = getSysUserId(request);
		String jsonString = form.getString("jsonWad");
		// System.out.println(jsonString);

		List<Analysis> newAnalysis = new ArrayList<>();
		List<Analysis> cancelAnalysis = new ArrayList<>();
		List<BatchTestStatusChangeBean> changeBeans = new ArrayList<>();
		StatusChangedMetaInfo changedMetaInfo = new StatusChangedMetaInfo();
		manageAnalysis(jsonString, cancelAnalysis, newAnalysis, changeBeans, changedMetaInfo);

		String cancelStatus = StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.Canceled);

		Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			for (Analysis analysis : cancelAnalysis) {
				analysis.setStatusId(cancelStatus);
				analysis.setSysUserId(getSysUserId(request));
				analysisDAO.updateData(analysis);
			}

			for (Analysis analysis : newAnalysis) {
				analysis.setSysUserId(getSysUserId(request));
				analysisDAO.insertData(analysis, false);
			}

			tx.commit();
		} catch (LIMSRuntimeException e) {
			tx.rollback();
		}

		if (changeBeans.isEmpty()) {
			return findForward(forward, form);
		} else {
			PropertyUtils.setProperty(form, "sampleList",
					DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
			PropertyUtils.setProperty(form, "statusChangedList", changeBeans);
			PropertyUtils.setProperty(form, "statusChangedSampleType", changedMetaInfo.sampleTypeName);
			PropertyUtils.setProperty(form, "statusChangedCurrentTest", changedMetaInfo.currentTest);
			PropertyUtils.setProperty(form, "statusChangedNextTest", changedMetaInfo.nextTest);
			return findForward("resubmit", form);
		}
	}

	private void manageAnalysis(String jsonString, List<Analysis> cancelAnalysis, List<Analysis> newAnalysis,
			List<BatchTestStatusChangeBean> changeBeans, StatusChangedMetaInfo changedMetaInfo) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject obj = (JSONObject) parser.parse(jsonString);
			List<Test> newTests = getNewTestsFromJson(obj, parser);
			List<Analysis> changedNotStarted = getAnalysisFromJson((String) obj.get("changeNotStarted"), parser);
			List<Analysis> noChangedNotStarted = getAnalysisFromJson((String) obj.get("noChangeNotStarted"), parser);
			List<Analysis> changeTechReject = getAnalysisFromJson((String) obj.get("changeTechReject"), parser);
			List<Analysis> noChangeTechReject = getAnalysisFromJson((String) obj.get("noChangeTechReject"), parser);
			List<Analysis> changeBioReject = getAnalysisFromJson((String) obj.get("changeBioReject"), parser);
			List<Analysis> noChangeBioReject = getAnalysisFromJson((String) obj.get("noChangeBioReject"), parser);
			List<Analysis> changeNotValidated = getAnalysisFromJson((String) obj.get("changeNotValidated"), parser);
			List<Analysis> noChangeNotValidated = getAnalysisFromJson((String) obj.get("noChangeNotValidated"), parser);

			verifyStatusNotChanged(changedNotStarted, noChangedNotStarted, StatusService.AnalysisStatus.NotStarted,
					changeBeans);
			verifyStatusNotChanged(changeNotValidated, noChangeNotValidated,
					StatusService.AnalysisStatus.TechnicalAcceptance, changeBeans);
			verifyStatusNotChanged(changeTechReject, noChangeTechReject, StatusService.AnalysisStatus.TechnicalRejected,
					changeBeans);
			verifyStatusNotChanged(changeBioReject, noChangeBioReject, StatusService.AnalysisStatus.BiologistRejected,
					changeBeans);

			cancelAnalysis.addAll(changedNotStarted);
			cancelAnalysis.addAll(changeBioReject);
			cancelAnalysis.addAll(changeNotValidated);
			cancelAnalysis.addAll(changeTechReject);

			if (!newTests.isEmpty()) {
				newAnalysis.addAll(createNewAnalysis(newTests, changedNotStarted));
				newAnalysis.addAll(createNewAnalysis(newTests, changeBioReject));
				newAnalysis.addAll(createNewAnalysis(newTests, changeNotValidated));
				newAnalysis.addAll(createNewAnalysis(newTests, changeTechReject));
			}

			if (!changeBeans.isEmpty()) {
				String newTestsString;
				if (newTests.isEmpty()) {
					newTestsString = MessageUtil.getMessage("status.test.canceled");
				} else {
					newTestsString = MessageUtil.getMessage("label.test.batch.reassignment") + ": "
							+ TestService.getUserLocalizedTestName(newTests.get(0));
					for (int i = 1; i < newTests.size(); i++) {
						newTestsString += ", " + TestService.getUserLocalizedTestName(newTests.get(i));
					}
				}
				changedMetaInfo.nextTest = newTestsString;
				changedMetaInfo.currentTest = (String) obj.get("current");
				changedMetaInfo.sampleTypeName = (String) obj.get("sampleType");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private List<Analysis> createNewAnalysis(List<Test> newTests, List<Analysis> changeAnalysis) {
		List<Analysis> newAnalysis = new ArrayList<>();
		for (Test test : newTests) {
			for (Analysis analysis : changeAnalysis) {
				newAnalysis.add(AnalysisService.buildAnalysis(test, analysis.getSampleItem()));
			}
		}

		return newAnalysis;
	}

	private void verifyStatusNotChanged(List<Analysis> changed, List<Analysis> noChanged,
			StatusService.AnalysisStatus status, List<BatchTestStatusChangeBean> changeBeans) {
		String statusId = StatusService.getInstance().getStatusID(status);

		List<Analysis> changedAnalysis = new ArrayList<>();

		for (Analysis analysis : changed) {
			if (!statusId.equals(analysis.getStatusId())) {
				changedAnalysis.add(analysis);
			}
		}

		if (!changedAnalysis.isEmpty()) {
			changed.removeAll(changedAnalysis);
		}

		for (Analysis analysis : noChanged) {
			if (!statusId.equals(analysis.getStatusId())) {
				changedAnalysis.add(analysis);
			}
		}

		if (!changedAnalysis.isEmpty()) {
			String oldStatus = getStatusName(status);

			for (Analysis analysis : changedAnalysis) {
				BatchTestStatusChangeBean bean = new BatchTestStatusChangeBean();
				bean.setLabNo(analysis.getSampleItem().getSample().getAccessionNumber());
				bean.setOldStatus(oldStatus);
				bean.setNewStatus(getStatusName(analysis.getStatusId()));
				changeBeans.add(bean);
			}
		}
	}

	private String getStatusName(String statusId) {
		StatusService.AnalysisStatus status = StatusService.getInstance().getAnalysisStatusForID(statusId);
		String name = getStatusName(status);
		return name == null ? StatusService.getInstance().getStatusName(status) : name;
	}

	private String getStatusName(StatusService.AnalysisStatus status) {
		switch (status) {
		case NotStarted:
			return MessageUtil.getMessage("label.analysisNotStarted");
		case TechnicalRejected:
			return MessageUtil.getMessage("label.rejectedByTechnician");
		case TechnicalAcceptance:
			return MessageUtil.getMessage("label.notValidated");
		case BiologistRejected:
			return MessageUtil.getMessage("label.rejectedByBiologist");
		default:
			return null;
		}
	}

	private List<Test> getNewTestsFromJson(JSONObject obj, JSONParser parser) {
		List<Test> replacementTestList = new ArrayList<>();

		String replacementTests = (String) obj.get("replace");
		if (replacementTests == null) {
			return replacementTestList;
		}

		JSONArray replacementTestArray;
		try {
			replacementTestArray = (JSONArray) parser.parse(replacementTests);
		} catch (ParseException e) {
			e.printStackTrace();
			return replacementTestList;
		}

		for (Object testIdObject : replacementTestArray) {
			replacementTestList.add(new TestService((String) testIdObject).getTest());
		}

		return replacementTestList;
	}

	private List<Analysis> getAnalysisFromJson(String sampleIdList, JSONParser parser) {
		List<Analysis> analysisList = new ArrayList<>();

		if (sampleIdList == null) {
			return analysisList;
		}

		JSONArray modifyAnalysisArray;
		try {
			modifyAnalysisArray = (JSONArray) parser.parse(sampleIdList);
		} catch (ParseException e) {
			e.printStackTrace();
			return analysisList;
		}

		for (Object analysisId : modifyAnalysisArray) {
			analysisList.add(analysisDAO.getAnalysisById((String) analysisId));
		}

		return analysisList;
	}

	@Override
	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		if (FWD_SUCCESS.equals(forward)) {
			return new ModelAndView("redirect:/BatchTestReassignment.do?forward=success", "form", form);
		} else if ("resubmit".equals(forward)) {
			return new ModelAndView("BatchTestReassignmentDefinition", "form", form);
		} else {
			return new ModelAndView("PageNotFound");
		}
	}

	@Override
	protected String getPageTitleKey() {
		return "configuration.batch.test.reassignment";
	}

	@Override
	protected String getPageSubtitleKey() {
		return "configuration.batch.test.reassignment";
	}

	private class StatusChangedMetaInfo {
		public String currentTest;
		public String nextTest;
		public String sampleTypeName;
	}
}
