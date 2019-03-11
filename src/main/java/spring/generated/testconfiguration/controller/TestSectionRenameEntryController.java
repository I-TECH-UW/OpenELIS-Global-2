package spring.generated.testconfiguration.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.forms.TestSectionRenameEntryForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class TestSectionRenameEntryController extends BaseController {
	@RequestMapping(
			value = "/TestSectionRenameEntry", 
			method = RequestMethod.GET
	)
	public ModelAndView showTestSectionRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") TestSectionRenameEntryForm form) {	
		String forward = FWD_SUCCESS;
		if (form == null ) {
			 form = new TestSectionRenameEntryForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();
		

		form.setTestSectionList(DisplayListService.getList(DisplayListService.ListType.TEST_SECTION));

		return findForward(forward, form);
	}

	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "testSectionRenameDefinition";
		} else {
			return "PageNotFound";
		}
	}
	
	@RequestMapping(
			value = "/TestSectionRenameEntry", 
			method = RequestMethod.POST
		)
	public ModelAndView updateTestSectionRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") TestSectionRenameEntryForm form) {	
		
		String forward = FWD_SUCCESS;

		String testSectionId = form.getTestSectionId();
		String nameEnglish = form.getNameEnglish();
		String nameFrench = form.getNameFrench();
		String userId = getSysUserId(request);

		updateTestSectionNames(testSectionId, nameEnglish, nameFrench, userId);

		form = new TestSectionRenameEntryForm();
		form.setFormAction("");

		form.setTestSectionList(DisplayListService.getList(DisplayListService.ListType.TEST_SECTION));

		return findForward(forward, form);
	}

	private void updateTestSectionNames(String testSectionId, String nameEnglish, String nameFrench, String userId) {
		TestSection testSection = new TestSectionDAOImpl().getTestSectionById(testSectionId);
		
		if (testSection != null) {

			Localization name = testSection.getLocalization();
			name.setEnglish(nameEnglish.trim());
			name.setFrench(nameFrench.trim());
			name.setSysUserId(userId);

			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
				new LocalizationDAOImpl().updateData(name);
				tx.commit();
			} catch (HibernateException e) {
				tx.rollback();
			} finally {
				HibernateUtil.closeSession();
			}

		}

		// Refresh Test Section names
		DisplayListService.getFreshList(DisplayListService.ListType.TEST_SECTION);
	}

	protected String getPageTitleKey() {
		return null;
	}

	protected String getPageSubtitleKey() {
		return null;
	}
}
