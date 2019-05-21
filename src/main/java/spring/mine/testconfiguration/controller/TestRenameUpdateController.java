package spring.mine.testconfiguration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.testconfiguration.form.TestRenameEntryForm;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.test.valueholder.Test;

@Controller
//TODO delete this, already in testrenameEntrycontroller.java
public class TestRenameUpdateController extends BaseController {
	@RequestMapping(value = "/TestRenameUpdate", method = RequestMethod.POST)
	public ModelAndView showTestRenameUpdate(@ModelAttribute("form") @Valid TestRenameEntryForm form,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		String forward = FWD_SUCCESS;

		if (result.hasErrors()) {
			saveErrors(result);
			forward = FWD_FAIL;
			return findForward(FWD_FAIL, form);
		}

		String testId = form.getTestId();
		String nameEnglish = form.getNameEnglish();
		String nameFrench = form.getNameFrench();
		String reportNameEnglish = form.getReportNameEnglish();
		String reportNameFrench = form.getReportNameFrench();
		String userId = getSysUserId(request);

		updateTestNames(testId, nameEnglish, nameFrench, reportNameEnglish, reportNameFrench, userId);

		form = new TestRenameEntryForm();
		form.setFormAction("");

		form.setTestList(DisplayListService.getList(DisplayListService.ListType.ALL_TESTS));

		return findForward(forward, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "testRenameDefinition";
		} else if (FWD_FAIL.equals(forward)) {
			return "redirect:/TestManagementConfigMenu.do";
		} else {
			return "PageNotFound";
		}
	}

	private void updateTestNames(String testId, String nameEnglish, String nameFrench, String reportNameEnglish,
			String reportNameFrench, String userId) {
		Test test = new TestService(testId).getTest();

		if (test != null) {

			Localization name = test.getLocalizedTestName();
			Localization reportingName = test.getLocalizedReportingName();
			name.setEnglish(nameEnglish.trim());
			name.setFrench(nameFrench.trim());
			name.setSysUserId(userId);
			reportingName.setEnglish(reportNameEnglish.trim());
			reportingName.setFrench(reportNameFrench.trim());
			reportingName.setSysUserId(userId);

			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
				new LocalizationDAOImpl().updateData(name);
				new LocalizationDAOImpl().updateData(reportingName);
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
			} finally {
				HibernateUtil.closeSession();
			}

		}

		// Refresh test names
		DisplayListService.getFreshList(DisplayListService.ListType.ALL_TESTS);
		DisplayListService.getFreshList(DisplayListService.ListType.ORDERABLE_TESTS);
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}
}
