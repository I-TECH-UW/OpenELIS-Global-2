/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

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
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.panel.daoimpl.PanelDAOImpl;
import org.openelisglobal.panel.valueholder.Panel;

public class SelectListCreateAction extends BaseAction {
    public static final String NAME_SEPARATOR = "$";
    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((DynaValidatorForm)form).initialize(mapping);
        PropertyUtils.setProperty(form, "existingPanelList", DisplayListService.getInstance().getList(DisplayListService.ListType.PANELS_ACTIVE));
        PropertyUtils.setProperty(form, "inactivePanelList", DisplayListService.getInstance().getList(DisplayListService.ListType.PANELS_INACTIVE));
        PropertyUtils.setProperty(form, "existingSampleTypeList", DisplayListService.getInstance().getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
        List<Panel> panels = new PanelDAOImpl().getAllPanels();
        PropertyUtils.setProperty(form, "existingEnglishNames", getExistingTestNames(panels, ConfigurationProperties.LOCALE.ENGLISH));
        PropertyUtils.setProperty(form, "existingFrenchNames", getExistingTestNames(panels, ConfigurationProperties.LOCALE.FRENCH));

        return mapping.findForward(FWD_SUCCESS);
    }

    private String getExistingTestNames(List<Panel> panels, ConfigurationProperties.LOCALE locale) {
        StringBuilder builder = new StringBuilder(NAME_SEPARATOR);

        for( Panel panel : panels){
            builder.append(LocalizationServiceImpl.getLocalizationValueByLocal(locale, panel.getLocalization()));
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
