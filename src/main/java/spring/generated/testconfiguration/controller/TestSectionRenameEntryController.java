package spring.generated.testconfiguration.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.testconfiguration.form.TestSectionRenameEntryForm;
import spring.mine.common.controller.BaseController;
import spring.service.localization.LocalizationService;
import spring.service.test.TestSectionService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class TestSectionRenameEntryController extends BaseController {
	
	@Autowired
	LocalizationService localizationService;
	@Autowired
	TestSectionService testSectionService;

	@RequestMapping(value = "/TestSectionRenameEntry", method = RequestMethod.GET)
	public ModelAndView showTestSectionRenameEntry(HttpServletRequest request) {
		TestSectionRenameEntryForm form = new TestSectionRenameEntryForm();

		form.setTestSectionList(DisplayListService.getInstance().getList(DisplayListService.ListType.TEST_SECTION));

		return findForward(FWD_SUCCESS, form);
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "testSectionRenameDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/TestSectionRenameEntry.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "testSectionRenameDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@RequestMapping(value = "/TestSectionRenameEntry", method = RequestMethod.POST)
	public ModelAndView updateTestSectionRenameEntry(HttpServletRequest request,
			@ModelAttribute("form") @Valid TestSectionRenameEntryForm form, BindingResult result) {
		if (result.hasErrors()) {
			saveErrors(result);
			form.setTestSectionList(DisplayListService.getInstance().getList(DisplayListService.ListType.TEST_SECTION));
			return findForward(FWD_FAIL_INSERT, form);
		}

		String testSectionId = form.getTestSectionId();
		String nameEnglish = form.getNameEnglish();
		String nameFrench = form.getNameFrench();
		String userId = getSysUserId(request);

		updateTestSectionNames(testSectionId, nameEnglish, nameFrench, userId);

		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private void updateTestSectionNames(String testSectionId, String nameEnglish, String nameFrench, String userId) {
		TestSection testSection = testSectionService.getTestSectionById(testSectionId);

		if (testSection != null) {

			Localization name = testSection.getLocalization();
			name.setEnglish(nameEnglish.trim());
			name.setFrench(nameFrench.trim());
			name.setSysUserId(userId);

//			Transaction tx = HibernateUtil.getSession().beginTransaction();

			try {
				localizationService.update(name);
//				tx.commit();
			} catch (HibernateException lre) {
//				tx.rollback();
				lre.printStackTrace();
			} 
//			finally {
//				HibernateUtil.closeSession();
//			}

		}

		// Refresh Test Section names
		DisplayListService.getInstance().getFreshList(DisplayListService.ListType.TEST_SECTION);
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
