package org.openelisglobal.testconfiguration.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.localization.service.LocalizationServiceImpl;
//import org.openelisglobal.test.service.TestSectionServiceImpl;
import org.openelisglobal.unitofmeasure.service.UnitOfMeasureServiceImpl;
import org.openelisglobal.common.util.ConfigurationProperties;
// import org.openelisglobal.test.valueholder.TestSection;
import org.openelisglobal.unitofmeasure.valueholder.UnitOfMeasure;

public class UomCreateAction extends BaseAction {
    public static final String NAME_SEPARATOR = "$";
    
    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((DynaValidatorForm)form).initialize(mapping);
        PropertyUtils.setProperty(form, "existingUomList", DisplayListService.getInstance().getList(DisplayListService.ListType.UNIT_OF_MEASURE));
        PropertyUtils.setProperty(form, "inactiveUomList", DisplayListService.getInstance().getList(DisplayListService.ListType.UNIT_OF_MEASURE_INACTIVE));
        List<UnitOfMeasure> uoms = UnitOfMeasureServiceImpl.getInstance().getAllUnitOfMeasures();
        PropertyUtils.setProperty(form, "existingEnglishNames", getExistingUomNames(uoms, ConfigurationProperties.LOCALE.ENGLISH));
        PropertyUtils.setProperty(form, "existingFrenchNames", getExistingUomNames(uoms, ConfigurationProperties.LOCALE.FRENCH));

        return mapping.findForward(FWD_SUCCESS);
    }

    
    private String getExistingUomNames(List<UnitOfMeasure> uoms, ConfigurationProperties.LOCALE locale) {
        StringBuilder builder = new StringBuilder(NAME_SEPARATOR);

        for( UnitOfMeasure uom : uoms){
            builder.append(LocalizationServiceImpl.getLocalizationValueByLocal(locale, uom.getLocalization()));
            builder.append(NAME_SEPARATOR);
        }

        return builder.toString();
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

