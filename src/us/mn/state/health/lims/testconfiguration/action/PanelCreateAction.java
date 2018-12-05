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

package us.mn.state.health.lims.testconfiguration.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.LocalizationService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;

public class PanelCreateAction extends BaseAction {
    public static final String NAME_SEPARATOR = "$";
    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ((DynaValidatorForm)form).initialize(mapping);

        HashMap<String, List<Panel>> existingSampleTypePanelMap = PanelTestConfigurationUtil.createTypeOfSamplePanelMap(true);
        HashMap<String, List<Panel>> inactiveSampleTypePanelMap = PanelTestConfigurationUtil.createTypeOfSamplePanelMap(false);
        PropertyUtils.setProperty(form, "existingSampleTypeList", DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE));
        List<Panel> panels = new PanelDAOImpl().getAllPanels();
        PropertyUtils.setProperty(form, "existingEnglishNames", getExistingTestNames(panels, ConfigurationProperties.LOCALE.ENGLISH));
        PropertyUtils.setProperty(form, "existingFrenchNames", getExistingTestNames(panels, ConfigurationProperties.LOCALE.FRENCH));
        
        List<SampleTypePanel> sampleTypePanelsExists = new ArrayList<SampleTypePanel>();
        List<SampleTypePanel> sampleTypePanelsInactive = new ArrayList<SampleTypePanel>();

        for (IdValuePair typeOfSample : DisplayListService.getList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE)) {
        	SampleTypePanel sampleTypePanel = new SampleTypePanel(typeOfSample.getValue());
        	sampleTypePanel.setPanels(existingSampleTypePanelMap.get(typeOfSample.getValue()));
        	sampleTypePanelsExists.add(sampleTypePanel);
        	SampleTypePanel sampleTypePanelInactive = new SampleTypePanel(typeOfSample.getValue());
        	sampleTypePanelInactive.setPanels(inactiveSampleTypePanelMap.get(typeOfSample.getValue()));
        	sampleTypePanelsInactive.add(sampleTypePanelInactive);

        	
        }
        PropertyUtils.setProperty(form, "existingPanelList", sampleTypePanelsExists);
        PropertyUtils.setProperty(form, "inactivePanelList", sampleTypePanelsInactive);
        
        return mapping.findForward(FWD_SUCCESS);
    }

    private String getExistingTestNames(List<Panel> panels, ConfigurationProperties.LOCALE locale) {
        StringBuilder builder = new StringBuilder(NAME_SEPARATOR);

        for( Panel panel : panels){
            builder.append(LocalizationService.getLocalizationValueByLocal(locale, panel.getLocalization()));
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
