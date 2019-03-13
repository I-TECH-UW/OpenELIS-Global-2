package spring.mine.testconfiguration.controller;

import java.lang.String;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import spring.mine.testconfiguration.form.TestRenameEntryForm;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.test.valueholder.Test;

@Controller
public class TestRenameEntryController extends BaseController {
	@RequestMapping(
			value = "/TestRenameEntry", 
			method = RequestMethod.GET
	)
	public ModelAndView showTestRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") TestRenameEntryForm form) {	
		String forward = FWD_SUCCESS;
		if (form == null ) {
			 form = new TestRenameEntryForm();
		}
		form.setFormAction("");
		BaseErrors errors = new BaseErrors();
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		form.setTestList(DisplayListService.getList(DisplayListService.ListType.ALL_TESTS));

		return findForward(forward, form);
	}

	protected ModelAndView findLocalForward(String forward, BaseForm form) {
		  if (FWD_SUCCESS.equals(forward)) {
		      return new ModelAndView("testRenameDefinition", "form", form);
		    } else if (FWD_SUCCESS_INSERT.equals(forward)) {
		      return new ModelAndView("redirect:/TestRenameEntry.do", "form", form);
		    } else {
		    	return new ModelAndView("PageNotFound");
		    }
	  }
	
	@RequestMapping(
			value = "/TestRenameEntry", 
			method = RequestMethod.POST
		)
	public ModelAndView updateTestRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") TestRenameEntryForm form) {	
		
		String forward = FWD_SUCCESS_INSERT;
		if (form == null) {
			form = new TestRenameEntryForm();
		}
		form.setFormAction("");
		form.setCancelAction("CancelDictionary.do");
		BaseErrors errors = new BaseErrors();
		if (form.getErrors() != null) {
			errors = (BaseErrors) form.getErrors();
		}
		ModelAndView mv = checkUserAndSetup(form, errors, request);

		if (errors.hasErrors()) {
			return mv;
		}

		String testId = form.getTestId();
		String nameEnglish = form.getNameEnglish();
		String nameFrench = form.getNameFrench();
		String reportNameEnglish = form.getReportNameEnglish();
		String reportNameFrench = form.getReportNameFrench();
		String userId = getSysUserId(request);

		updateTestNames(testId, nameEnglish, nameFrench, reportNameEnglish, reportNameFrench, userId);

		return findForward(forward, form);
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

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}
}
