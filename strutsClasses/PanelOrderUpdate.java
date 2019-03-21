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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;


public class PanelOrderUpdate extends BaseAction {
    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String changeList = ((DynaValidatorForm) form).getString("jsonChangeList");

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(changeList);
        List<ActivateSet> orderSet = getActivateSetForActions("panels", obj, parser);
        List<Panel> panels = new ArrayList<Panel>();

        String currentUserId = getSysUserId(request);
        PanelDAO panelDAO = new PanelDAOImpl();
        for (ActivateSet sets : orderSet) {
            Panel panel = panelDAO.getPanelById(sets.id);
            panel.setSortOrderInt(sets.sortOrder); 
            panel.setSysUserId(currentUserId);
            panels.add(panel);
        }


        Transaction tx = HibernateUtil.getSession().beginTransaction();
        try {
            for (Panel panel : panels) {
            	panelDAO.updateData(panel);
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

        DisplayListService.refreshList(DisplayListService.ListType.PANELS);
        DisplayListService.refreshList(DisplayListService.ListType.PANELS_INACTIVE);
        DisplayListService.refreshList(DisplayListService.ListType.PANELS_ACTIVE);

        return mapping.findForward(FWD_SUCCESS);
    }

    private List<ActivateSet> getActivateSetForActions(String key, JSONObject root, JSONParser parser) {
        List<ActivateSet> list = new ArrayList<ActivateSet>();

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
}
