package spring.generated.testconfiguration.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.testconfiguration.form.TestActivationForm;
import spring.generated.testconfiguration.validator.TestActivationFormValidator;
import spring.mine.common.controller.BaseController;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.beanItems.TestActivationBean;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Controller
public class TestActivationController extends BaseController {

	@Autowired
	TestActivationFormValidator formValidator;

	@RequestMapping(value = "/TestActivation", method = RequestMethod.GET)
	public ModelAndView showTestActivation(HttpServletRequest request) {
		TestActivationForm form = new TestActivationForm();

		setupDisplayItems(form);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupDisplayItems(TestActivationForm form) {
		List<TestActivationBean> activeTestList = createTestList(true, false);
		List<TestActivationBean> inactiveTestList = createTestList(false, false);
		form.setActiveTestList(activeTestList);
		form.setInactiveTestList(inactiveTestList);
	}

	private List<TestActivationBean> createTestList(boolean active, boolean refresh) {
		ArrayList<TestActivationBean> testList = new ArrayList<>();

		if (refresh) {
			DisplayListService.refreshList(active ? DisplayListService.ListType.SAMPLE_TYPE_ACTIVE
					: DisplayListService.ListType.SAMPLE_TYPE_INACTIVE);
		}

		List<IdValuePair> sampleTypeList = DisplayListService
				.getList(active ? DisplayListService.ListType.SAMPLE_TYPE_ACTIVE
						: DisplayListService.ListType.SAMPLE_TYPE_INACTIVE);

		// if not active we use alphabetical ordering, the default is display order
		if (!active) {
			IdValuePair.sortByValue(sampleTypeList);
		}

		for (IdValuePair pair : sampleTypeList) {
			TestActivationBean bean = new TestActivationBean();

			List<Test> tests = TypeOfSampleService.getAllTestsBySampleTypeId(pair.getId());
			List<IdValuePair> activeTests = new ArrayList<>();
			List<IdValuePair> inactiveTests = new ArrayList<>();

			// initial ordering will be by display order. Inactive tests will then be
			// re-ordered alphabetically
			Collections.sort(tests, new Comparator<Test>() {
				@Override
				public int compare(Test o1, Test o2) {
					// compare sort order
					if (NumberUtils.isNumber(o1.getSortOrder()) && NumberUtils.isNumber(o2.getSortOrder())) {
						return Integer.parseInt(o1.getSortOrder()) - Integer.parseInt(o2.getSortOrder());
						// if o2 has no sort order o1 does, o2 is assumed to be higher
					} else if (NumberUtils.isNumber(o1.getSortOrder())) {
						return -1;
						// if o1 has no sort order o2 does, o1 is assumed to be higher
					} else if (NumberUtils.isNumber(o2.getSortOrder())) {
						return 1;
						// else they are considered equal
					} else {
						return 0;
					}
				}
			});

			for (Test test : tests) {
				if (test.isActive()) {
					activeTests.add(new IdValuePair(test.getId(), TestService.getUserLocalizedTestName(test)));
				} else {
					inactiveTests.add(new IdValuePair(test.getId(), TestService.getUserLocalizedTestName(test)));
				}
			}

			IdValuePair.sortByValue(inactiveTests);

			bean.setActiveTests(activeTests);
			bean.setInactiveTests(inactiveTests);
			if (!activeTests.isEmpty() || !inactiveTests.isEmpty()) {
				bean.setSampleType(pair);
				testList.add(bean);
			}
		}

		return testList;
	}

	@RequestMapping(value = "/TestActivation", method = RequestMethod.POST)
	public ModelAndView postTestActivation(HttpServletRequest request, @ModelAttribute("form") TestActivationForm form,
			BindingResult result) throws Exception {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			setupDisplayItems(form);
			return findForward(FWD_FAIL_INSERT, form);
		}

		String changeList = form.getJsonChangeList();

		JSONParser parser = new JSONParser();

		JSONObject obj = (JSONObject) parser.parse(changeList);

		List<ActivateSet> activateSampleSets = getActivateSetForActions("activateSample", obj, parser);
		List<String> deactivateSampleIds = getIdsForActions("deactivateSample", obj, parser);
		List<ActivateSet> activateTestSets = getActivateSetForActions("activateTest", obj, parser);
		List<String> deactivateTestIds = getIdsForActions("deactivateTest", obj, parser);

		List<Test> deactivateTests = getDeactivatedTests(deactivateTestIds);
		List<Test> activateTests = getActivatedTests(activateTestSets);
		List<TypeOfSample> deactivateSampleTypes = getDeactivatedSampleTypes(deactivateSampleIds);
		List<TypeOfSample> activateSampleTypes = getActivatedSampleTypes(activateSampleSets);

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		TestDAO testDAO = new TestDAOImpl();
		TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();

		try {
			for (Test test : deactivateTests) {
				testDAO.updateData(test);
			}

			for (Test test : activateTests) {
				testDAO.updateData(test);
			}

			for (TypeOfSample typeOfSample : deactivateSampleTypes) {
				typeOfSampleDAO.updateData(typeOfSample);
			}

			for (TypeOfSample typeOfSample : activateSampleTypes) {
				typeOfSampleDAO.updateData(typeOfSample);
			}

			if (!deactivateSampleTypes.isEmpty() || !activateSampleTypes.isEmpty()) {
				TypeOfSampleService.clearCache();
			}

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}

		List<TestActivationBean> activeTestList = createTestList(true, true);
		List<TestActivationBean> inactiveTestList = createTestList(false, true);
		form.setActiveTestList(activeTestList);
		form.setInactiveTestList(inactiveTestList);

		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private List<Test> getDeactivatedTests(List<String> testIds) {
		List<Test> tests = new ArrayList<>();

		for (String testId : testIds) {
			Test test = new TestService(testId).getTest();
			test.setIsActive("N");
			test.setSysUserId(getSysUserId(request));
			tests.add(test);
		}

		return tests;
	}

	private List<Test> getActivatedTests(List<ActivateSet> testIds) {
		List<Test> tests = new ArrayList<>();

		for (ActivateSet set : testIds) {
			Test test = new TestService(set.id).getTest();
			test.setIsActive("Y");
			test.setSortOrder(String.valueOf(set.sortOrder * 10));
			test.setSysUserId(getSysUserId(request));
			tests.add(test);
		}

		return tests;
	}

	private List<TypeOfSample> getDeactivatedSampleTypes(List<String> sampleTypeIds) {
		List<TypeOfSample> sampleTypes = new ArrayList<>();

		for (String id : sampleTypeIds) {
			TypeOfSample typeOfSample = TypeOfSampleService.getTransientTypeOfSampleById(id);
			typeOfSample.setActive(false);
			typeOfSample.setSysUserId(getSysUserId(request));
			sampleTypes.add(typeOfSample);
		}

		return sampleTypes;
	}

	private List<TypeOfSample> getActivatedSampleTypes(List<ActivateSet> sampleTypeSets) {
		List<TypeOfSample> sampleTypes = new ArrayList<>();

		for (ActivateSet set : sampleTypeSets) {
			TypeOfSample typeOfSample = TypeOfSampleService.getTransientTypeOfSampleById(set.id);
			typeOfSample.setActive(true);
			typeOfSample.setSortOrder(set.sortOrder * 10);
			typeOfSample.setSysUserId(getSysUserId(request));
			sampleTypes.add(typeOfSample);
		}

		return sampleTypes;
	}

	private List<String> getIdsForActions(String key, JSONObject root, JSONParser parser) {
		List<String> list = new ArrayList<>();

		String action = (String) root.get(key);

		try {
			JSONArray actionArray = (JSONArray) parser.parse(action);

			for (int i = 0; i < actionArray.size(); i++) {
				list.add((String) ((JSONObject) actionArray.get(i)).get("id"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return list;
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

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "testActivationDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "testActivationDefinition";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "testActivationDefinition";
		} else {
			return "PageNotFound";
		}
	}

	private class ActivateSet {
		public String id;
		public Integer sortOrder;
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
