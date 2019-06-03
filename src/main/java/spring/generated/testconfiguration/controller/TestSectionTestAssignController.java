package spring.generated.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.testconfiguration.form.TestSectionTestAssignForm;
import spring.mine.common.controller.BaseController;
import spring.service.test.TestSectionService;
import spring.service.test.TestSectionServiceImpl;
import spring.service.test.TestService;
import spring.service.test.TestServiceImpl;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class TestSectionTestAssignController extends BaseController {
	
	@Autowired
	TestService testService;
	@Autowired
	TestSectionService testSectionService;

	@RequestMapping(value = "/TestSectionTestAssign", method = RequestMethod.GET)
	public ModelAndView showTestSectionTestAssign(HttpServletRequest request) {
		TestSectionTestAssignForm form = new TestSectionTestAssignForm();

		setupDisplayItems(form);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupDisplayItems(TestSectionTestAssignForm form) {
		List<IdValuePair> testSections = DisplayListService.getInstance()
				.getListWithLeadingBlank(DisplayListService.ListType.TEST_SECTION);
		LinkedHashMap<IdValuePair, List<IdValuePair>> testSectionTestsMap = new LinkedHashMap<>(testSections.size());

		for (IdValuePair sectionPair : testSections) {
			List<IdValuePair> tests = new ArrayList<>();
			testSectionTestsMap.put(sectionPair, tests);
			List<Test> testList = TestSectionServiceImpl.getTestsInSection(sectionPair.getId());

			for (Test test : testList) {
				if (test.isActive()) {
					tests.add(new IdValuePair(test.getId(), TestServiceImpl.getLocalizedTestNameWithType(test)));
				}
			}
		}

		// we can't just append the original list because that list is in the cache
		List<IdValuePair> joinedList = new ArrayList<>(testSections);
		joinedList.addAll(DisplayListService.getInstance().getList(DisplayListService.ListType.TEST_SECTION_INACTIVE));
		try {
			PropertyUtils.setProperty(form, "testSectionList", joinedList);
			PropertyUtils.setProperty(form, "sectionTestList", testSectionTestsMap);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "testSectionAssignDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/TestSectionTestAssign.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "testSectionAssignDefinition";
		} else {
			return "PageNotFound";
		}
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}

	@RequestMapping(value = "/TestSectionTestAssign", method = RequestMethod.POST)
	public ModelAndView postTestSectionTestAssign(HttpServletRequest request,
			@ModelAttribute("form") @Valid TestSectionTestAssignForm form, BindingResult result) {
		if (result.hasErrors()) {
			saveErrors(result);
			setupDisplayItems(form);
			return findForward(FWD_FAIL_INSERT, form);
		}

		String testId = form.getString("testId");
		String testSectionId = form.getString("testSectionId");
		String deactivateTestSectionId = form.getString("deactivateTestSectionId");
		boolean updateTestSection = false;
		String currentUser = getSysUserId(request);
		Test test = new TestServiceImpl(testId).getTest();
		TestSection testSection = new TestSectionServiceImpl(testSectionId).getTestSection();
		TestSection deActivateTestSection = null;
		test.setTestSection(testSection);
		test.setSysUserId(currentUser);

		// This covers the case that they are moving the test to the same test section
		// they are moving it from
		if (testSectionId.equals(deactivateTestSectionId)) {
			return findForward(FWD_SUCCESS_INSERT, form);
		}

		if ("N".equals(testSection.getIsActive())) {
			testSection.setIsActive("Y");
			testSection.setSysUserId(currentUser);
			updateTestSection = true;
		}

		if (!GenericValidator.isBlankOrNull(deactivateTestSectionId)) {
			deActivateTestSection = new TestSectionServiceImpl(deactivateTestSectionId).getTestSection();
			deActivateTestSection.setIsActive("N");
			deActivateTestSection.setSysUserId(currentUser);
		}

//		Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			testService.update(test);

			if (updateTestSection) {
				testSectionService.update(testSection);
			}

			if (deActivateTestSection != null) {
				testSectionService.update(deActivateTestSection);
			}
//			tx.commit();
		} catch (HibernateException lre) {
//			tx.rollback();
			lre.printStackTrace();
		} 
//		finally {
//			HibernateUtil.closeSession();
//		}

		DisplayListService.getInstance().refreshList(DisplayListService.ListType.TEST_SECTION);
		DisplayListService.getInstance().refreshList(DisplayListService.ListType.TEST_SECTION_INACTIVE);

		return findForward(FWD_SUCCESS_INSERT, form);
	}
}
