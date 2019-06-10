package spring.generated.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import spring.generated.testconfiguration.form.PanelOrderForm;
import spring.generated.testconfiguration.validator.PanelOrderFormValidator;
import spring.mine.common.controller.BaseController;
import spring.service.panel.PanelService;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panel.valueholder.PanelSortOrderComparator;
import us.mn.state.health.lims.testconfiguration.action.PanelTestConfigurationUtil;
import us.mn.state.health.lims.testconfiguration.action.SampleTypePanel;

@Controller
public class PanelOrderController extends BaseController {

	@Autowired
	PanelOrderFormValidator formValidator;
	@Autowired
	PanelService panelService;
	@Autowired
	PanelTestConfigurationUtil panelTestConfigurationUtil;

	@RequestMapping(value = "/PanelOrder", method = RequestMethod.GET)
	public ModelAndView showPanelOrder(HttpServletRequest request) {
		PanelOrderForm form = new PanelOrderForm();

		setupDisplayItems(form);

		return findForward(FWD_SUCCESS, form);
	}

	protected void setupDisplayItems(PanelOrderForm form) {
		try {
			PropertyUtils.setProperty(form, "panelList",
					DisplayListService.getInstance().getList(DisplayListService.ListType.PANELS));
			PropertyUtils.setProperty(form, "existingSampleTypeList",
					DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HashMap<String, List<Panel>> existingSampleTypePanelMap = panelTestConfigurationUtil
				.createTypeOfSamplePanelMap(true);
		HashMap<String, List<Panel>> inactiveSampleTypePanelMap = panelTestConfigurationUtil
				.createTypeOfSamplePanelMap(false);
//		List<Panel> panels = new PanelDAOImpl().getAllPanels();
//		gnr: local var panels is never used
//		List<Panel> panels = panelService.getAllPanels();
		List<SampleTypePanel> sampleTypePanelsExists = new ArrayList<>();
		List<SampleTypePanel> sampleTypePanelsInactive = new ArrayList<>();

		for (IdValuePair typeOfSample : DisplayListService.getInstance()
				.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE)) {
			SampleTypePanel sampleTypePanel = new SampleTypePanel(typeOfSample.getValue());
			sampleTypePanel.setPanels(existingSampleTypePanelMap.get(typeOfSample.getValue()));
			if (sampleTypePanel.getPanels() != null && sampleTypePanel.getPanels().size() > 0) {
				Collections.sort(sampleTypePanel.getPanels(), PanelSortOrderComparator.SORT_ORDER_COMPARATOR);
			}

			sampleTypePanelsExists.add(sampleTypePanel);
			SampleTypePanel sampleTypePanelInactive = new SampleTypePanel(typeOfSample.getValue());
			sampleTypePanelInactive.setPanels(inactiveSampleTypePanelMap.get(typeOfSample.getValue()));
			if (sampleTypePanelInactive.getPanels() != null && sampleTypePanelInactive.getPanels().size() > 0) {
				Collections.sort(sampleTypePanelInactive.getPanels(), PanelSortOrderComparator.SORT_ORDER_COMPARATOR);
			}

			sampleTypePanelsInactive.add(sampleTypePanelInactive);
		}

		try {
			PropertyUtils.setProperty(form, "existingPanelList", sampleTypePanelsExists);
			PropertyUtils.setProperty(form, "inactivePanelList", sampleTypePanelsInactive);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/PanelOrder", method = RequestMethod.POST)
	public ModelAndView postPanelOrder(HttpServletRequest request, @ModelAttribute("form") @Valid PanelOrderForm form,
			BindingResult result) throws Exception {
		formValidator.validate(form, result);
		if (result.hasErrors()) {
			saveErrors(result);
			setupDisplayItems(form);
			return findForward(FWD_FAIL_INSERT, form);
		}

		String changeList = form.getString("jsonChangeList");

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(changeList);
		List<ActivateSet> orderSet = getActivateSetForActions("panels", obj, parser);
		List<Panel> panels = new ArrayList<>();

		String currentUserId = getSysUserId(request);
//		PanelDAO panelDAO = new PanelDAOImpl();
		for (ActivateSet sets : orderSet) {
			Panel panel = panelService.getPanelById(sets.id);
			panel.setSortOrderInt(sets.sortOrder);
			panel.setSysUserId(currentUserId);
			panels.add(panel);
		}

		try {
			panelService.updateAll(panels);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		DisplayListService.getInstance().refreshList(DisplayListService.ListType.PANELS);
		DisplayListService.getInstance().refreshList(DisplayListService.ListType.PANELS_INACTIVE);
		DisplayListService.getInstance().refreshList(DisplayListService.ListType.PANELS_ACTIVE);

		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private class ActivateSet {
		public String id;
		public Integer sortOrder;
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
			return "panelOrderDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/PanelOrder.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "panelOrderDefinition";
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
