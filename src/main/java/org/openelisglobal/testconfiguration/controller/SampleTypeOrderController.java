package org.openelisglobal.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
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

import org.openelisglobal.testconfiguration.form.SampleTypeOrderForm;
import org.openelisglobal.testconfiguration.validator.SampleTypeOrderFormValidator;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.typeofsample.valueholder.TypeOfSample;

@Controller
public class SampleTypeOrderController extends BaseController {

	@Autowired
	SampleTypeOrderFormValidator formValidator;
	@Autowired
	TypeOfSampleService typeOfSampleService;

	@RequestMapping(value = "/SampleTypeOrder", method = RequestMethod.GET)
	public ModelAndView showSampleTypeOrder(HttpServletRequest request) {
		SampleTypeOrderForm form = new SampleTypeOrderForm();

		setupDisplayItems(form);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupDisplayItems(SampleTypeOrderForm form) {
		try {
			PropertyUtils.setProperty(form, "sampleTypeList",
					DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private class ActivateSet {
		public String id;
		public Integer sortOrder;
	}

	@RequestMapping(value = "/SampleTypeOrder", method = RequestMethod.POST)
	public ModelAndView postSampleTypeOrder(HttpServletRequest request,
			@ModelAttribute("form") @Valid SampleTypeOrderForm form, BindingResult result) throws Exception {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			setupDisplayItems(form);
			return findForward(FWD_FAIL_INSERT, form);
		}

		String changeList = form.getJsonChangeList();

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(changeList);
		List<ActivateSet> orderSet = getActivateSetForActions("sampleTypes", obj, parser);
		List<TypeOfSample> typeOfSamples = new ArrayList<>();

		String currentUserId = getSysUserId(request);
		for (ActivateSet sets : orderSet) {
			TypeOfSample typeOfSample = typeOfSampleService.getTypeOfSampleById(sets.id);
			typeOfSample.setSortOrder(sets.sortOrder);
			typeOfSample.setSysUserId(currentUserId);
			typeOfSamples.add(typeOfSample);
		}

		try {
			typeOfSampleService.updateAll(typeOfSamples);
		} catch (HibernateException lre) {
			lre.printStackTrace();
		}

		DisplayListService.getInstance().refreshList(DisplayListService.ListType.SAMPLE_TYPE);
		DisplayListService.getInstance().refreshList(DisplayListService.ListType.SAMPLE_TYPE_INACTIVE);

		return findForward(FWD_SUCCESS_INSERT, form);
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
			return "sampleTypeOrderDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/SampleTypeOrder.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "sampleTypeOrderDefinition";
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
}
