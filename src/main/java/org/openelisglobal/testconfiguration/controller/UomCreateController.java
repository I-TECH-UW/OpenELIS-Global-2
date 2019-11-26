package org.openelisglobal.testconfiguration.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.openelisglobal.common.controller.BaseController;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.testconfiguration.form.UomCreateForm;
import org.openelisglobal.unitofmeasure.service.UnitOfMeasureService;
import org.openelisglobal.unitofmeasure.valueholder.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UomCreateController extends BaseController {

    public static final String NAME_SEPARATOR = "$";

    @Autowired
    UnitOfMeasureService unitOfMeasureService;

    @RequestMapping(value = "/UomCreate", method = RequestMethod.GET)
    public ModelAndView showUomCreate(HttpServletRequest request) {
        UomCreateForm form = new UomCreateForm();

        setupDisplayItems(form);

        return findForward(FWD_SUCCESS, form);
    }

    private void setupDisplayItems(UomCreateForm form) {
        try {
            PropertyUtils.setProperty(form, "existingUomList",
                    DisplayListService.getInstance().getList(DisplayListService.ListType.UNIT_OF_MEASURE));
            PropertyUtils.setProperty(form, "inactiveUomList",
                    DisplayListService.getInstance().getList(DisplayListService.ListType.UNIT_OF_MEASURE_INACTIVE));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.getMessage(), e);
        }
        List<UnitOfMeasure> uoms = unitOfMeasureService.getAll();
        try {
            PropertyUtils.setProperty(form, "existingEnglishNames", getExistingUomNames(uoms, Locale.ENGLISH));
            PropertyUtils.setProperty(form, "existingFrenchNames", getExistingUomNames(uoms, Locale.FRENCH));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LogEvent.logError(e.getMessage(), e);
        }
    }

    private String getExistingUomNames(List<UnitOfMeasure> uoms, Locale locale) {
        StringBuilder builder = new StringBuilder(NAME_SEPARATOR);

        for (UnitOfMeasure uom : uoms) {
            builder.append(uom.getLocalization().getLocalizedValue(locale));
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

        try {
            unitOfMeasureService.insert(unitOfMeasure);
        } catch (LIMSRuntimeException e) {
            e.printStackTrace();
        }

        DisplayListService.getInstance().refreshList(DisplayListService.ListType.UNIT_OF_MEASURE);
        DisplayListService.getInstance().refreshList(DisplayListService.ListType.UNIT_OF_MEASURE_INACTIVE);

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
