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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.TestService;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.panel.dao.PanelDAO;
import org.openelisglobal.panel.daoimpl.PanelDAOImpl;
import org.openelisglobal.panel.valueholder.Panel;
import org.openelisglobal.panelitem.dao.PanelItemDAO;
import org.openelisglobal.panelitem.daoimpl.PanelItemDAOImpl;
import org.openelisglobal.panelitem.valueholder.PanelItem;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.test.valueholder.TestComparator;

public class PanelTestAssignAction extends BaseAction {
    @Override  
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    	String panelId = dynaForm.getString("panelId");
    	if (panelId == null) {
    		panelId = "";
    	}
    	((DynaValidatorForm)form).initialize(mapping);
        List<IdValuePair> panels = DisplayListService.getInstance().getList(DisplayListService.ListType.PANELS);
        
        if (!GenericValidator.isBlankOrNull(panelId)) {
            PanelDAO panelDAO = new PanelDAOImpl();
            Panel panel = panelDAO.getPanelById(panelId);
        	IdValuePair panelPair = new IdValuePair(panelId, panel.getLocalizedName());

        	List<IdValuePair> tests = new ArrayList<IdValuePair>();

            List<Test> testList = getAllTestsByPanelId(panelId);
            
            PanelTests panelTests = new PanelTests(panelPair);
            HashSet<String> testIdSet = new HashSet<String>();
            
            for( Test test : testList){
                if( test.isActive()) {
                    tests.add(new IdValuePair(test.getId(), TestServiceImpl.getUserLocalizedTestName(test)));
                    testIdSet.add(test.getId());
                }
            }
            
            panelTests.setTests(tests, testIdSet);
            PropertyUtils.setProperty(form, "selectedPanel", panelTests);
        }  else {
        	PropertyUtils.setProperty(form, "selectedPanel", new PanelTests());
        }
        
        
        PropertyUtils.setProperty(form, "panelList", panels);
        
        return mapping.findForward(FWD_SUCCESS);
    }

    public static List<Test> getAllTestsByPanelId(String panelId){
        List<Test> testList = new ArrayList<Test>();
        PanelItemDAO panelItemDAO = new PanelItemDAOImpl();

        @SuppressWarnings("unchecked")
		List<PanelItem> testLinks = panelItemDAO.getPanelItemsForPanel(panelId);

        for (PanelItem link : testLinks) {
            testList.add(link.getTest());
        }

        Collections.sort(testList, TestComparator.NAME_COMPARATOR);
        return testList;
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
