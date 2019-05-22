package spring.generated.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.generated.testconfiguration.form.UomCreateForm;
import spring.mine.common.controller.BaseController;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.services.DisplayListService;
import spring.service.localization.LocalizationServiceImpl;
import spring.service.unitofmeasure.UnitOfMeasureServiceImpl;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

@Controller
public class UomCreateController extends BaseController {

	public static final String NAME_SEPARATOR = "$";

	@RequestMapping(value = "/UomCreate", method = RequestMethod.GET)
	public ModelAndView showUomCreate(HttpServletRequest request) {
		UomCreateForm form = new UomCreateForm();

		setupDisplayItems(form);

		return findForward(FWD_SUCCESS, form);
	}

	private void setupDisplayItems(UomCreateForm form) {
		try {
			PropertyUtils.setProperty(form, "existingUomList",
					DisplayListService.getList(DisplayListService.ListType.UNIT_OF_MEASURE));
			PropertyUtils.setProperty(form, "inactiveUomList",
					DisplayListService.getList(DisplayListService.ListType.UNIT_OF_MEASURE_INACTIVE));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<UnitOfMeasure> uoms = UnitOfMeasureServiceImpl.getAllUnitOfMeasures();
		try {
			PropertyUtils.setProperty(form, "existingEnglishNames",
					getExistingUomNames(uoms, ConfigurationProperties.LOCALE.ENGLISH));
			PropertyUtils.setProperty(form, "existingFrenchNames",
					getExistingUomNames(uoms, ConfigurationProperties.LOCALE.FRENCH));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getExistingUomNames(List<UnitOfMeasure> uoms, ConfigurationProperties.LOCALE locale) {
		StringBuilder builder = new StringBuilder(NAME_SEPARATOR);

		for (UnitOfMeasure uom : uoms) {
			builder.append(LocalizationServiceImpl.getLocalizationValueByLocal(locale, uom.getLocalization()));
			builder.append(NAME_SEPARATOR);
		}

		return builder.toString();
	}

	@RequestMapping(value = "/UomCreate", method = RequestMethod.POST)
	public ModelAndView postUomCreate(HttpServletRequest request, @ModelAttribute("form") @Valid UomCreateForm form,
			BindingResult result) throws Exception {
		if (result.hasErrors()) {
			saveErrors(result);
			setupDisplayItems(form);
			return findForward(FWD_FAIL_INSERT, form);
		}

		String identifyingName = form.getUomEnglishName();
		String userId = getSysUserId(request);

		// Localization localization =
		// createLocalization(dynaForm.getString("uomFrenchName"), identifyingName,
		// userId);

		UnitOfMeasure unitOfMeasure = createUnitOfMeasure(identifyingName, userId);

		Transaction tx = HibernateUtil.getSession().beginTransaction();

		try {
			new UnitOfMeasureDAOImpl().insertData(unitOfMeasure);
			tx.commit();

		} catch (LIMSRuntimeException lre) {
			tx.rollback();
			lre.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

		DisplayListService.refreshList(DisplayListService.ListType.UNIT_OF_MEASURE);
		DisplayListService.refreshList(DisplayListService.ListType.UNIT_OF_MEASURE_INACTIVE);

		return findForward(FWD_SUCCESS_INSERT, form);
	}

	private UnitOfMeasure createUnitOfMeasure(String identifyingName, String userId) {
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setDescription(identifyingName);
		unitOfMeasure.setUnitOfMeasureName(identifyingName);
		return unitOfMeasure;
	}

	@Override
	protected String findLocalForward(String forward) {
		if (FWD_SUCCESS.equals(forward)) {
			return "uomCreateDefinition";
		} else if (FWD_SUCCESS_INSERT.equals(forward)) {
			return "redirect:/UomCreate.do";
		} else if (FWD_FAIL_INSERT.equals(forward)) {
			return "uomCreateDefinition";
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
