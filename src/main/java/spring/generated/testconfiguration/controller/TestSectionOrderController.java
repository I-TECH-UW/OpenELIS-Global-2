package spring.generated.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
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

import spring.generated.forms.TestSectionOrderForm;
import spring.mine.common.controller.BaseController;
import spring.mine.common.form.BaseForm;
import spring.mine.common.validator.BaseErrors;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.dao.TestSectionDAO;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Controller
public class TestSectionOrderController extends BaseController {
	@RequestMapping(value = "/TestSectionOrder", method = RequestMethod.GET)
	public ModelAndView showTestSectionOrder(HttpServletRequest request,
			@ModelAttribute("form") TestSectionOrderForm form) {
		String forward = FWD_SUCCESS;
		if (form == null) {
			form = new TestSectionOrderForm();
		}
		form.setFormAction("");
		Errors errors = new BaseErrors();

		try {
			PropertyUtils.setProperty(form, "testSectionList",
					DisplayListService.getList(DisplayListService.ListType.TEST_SECTION));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		return findForward(forward, form);
	}

	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "testSectionOrderDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/TestSectionOrder.do";
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

	private class ActivateSet {
		public String id;
		public Integer sortOrder;
	}

	@RequestMapping(value = "/TestSectionOrder", method = RequestMethod.POST)
	public ModelAndView postTestSectionOrder(HttpServletRequest request,
			@ModelAttribute("form") TestSectionOrderForm form) throws Exception {

		String forward = FWD_SUCCESS_INSERT;

		BaseErrors errors = new BaseErrors();

		String changeList = form.getJsonChangeList();

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(changeList);
		List<ActivateSet> orderSet = getActivateSetForActions("testSections", obj, parser);
		List<TestSection> testSections = new ArrayList<>();

		String currentUserId = getSysUserId(request);
		TestSectionDAO testSectionDAO = new TestSectionDAOImpl();
		for (ActivateSet sets : orderSet) {
			TestSection testSection = testSectionDAO.getTestSectionById(sets.id);
			testSection.setSortOrderInt(sets.sortOrder);
			testSection.setSysUserId(currentUserId);
			testSections.add(testSection);
		}

		Transaction tx = HibernateUtil.getSession().beginTransaction();
		try {
			for (TestSection testSection : testSections) {
				testSectionDAO.updateData(testSection);
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}

		DisplayListService.refreshList(DisplayListService.ListType.TEST_SECTION);
		DisplayListService.refreshList(DisplayListService.ListType.TEST_SECTION_INACTIVE);

		return findForward(forward, form);
	}

	private List<ActivateSet> getActivateSetForActions(String key, JSONObject root, JSONParser parser) {
		List<ActivateSet> list = new ArrayList<>();

		String action = (String) root.get(key);

		try {
			JSONArray actionArray = (JSONArray) parser.parse(action);

			for (int i = 0; i < actionArray.size(); i++) {
				ActivateSet set = new ActivateSet();
				set.id = String.valueOf(((JSONObject) actionArray.get(i)).get("id"));
				Long longSort = (Long) ((JSONObject) actionArray.get(i)).get("sortOrder");
				set.sortOrder = longSort.intValue();
				list.add(set);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return list;
	}
}
