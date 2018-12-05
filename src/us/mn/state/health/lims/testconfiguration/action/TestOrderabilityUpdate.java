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
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;


public class TestOrderabilityUpdate extends BaseAction {

    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String changeList = ((DynaValidatorForm) form).getString("jsonChangeList");

        JSONParser parser=new JSONParser();

        JSONObject obj = (JSONObject)parser.parse(changeList);

        List<String> orderableTestIds = getIdsForActions("activateTest", obj, parser);
        List<String> unorderableTestIds = getIdsForActions("deactivateTest", obj, parser);

        List<Test> tests = getTests(unorderableTestIds, false);
        tests.addAll(getTests(orderableTestIds, true));

        Transaction tx = HibernateUtil.getSession().beginTransaction();

        TestDAO testDAO = new TestDAOImpl();

        try{
            for(Test test : tests){
                testDAO.updateData(test);
            }

            tx.commit();
        }catch( HibernateException e ){
            tx.rollback();
        }finally{
            HibernateUtil.closeSession();
        }

        TypeOfSampleService.clearCache();
        return mapping.findForward(FWD_SUCCESS);
    }



    private List<Test> getTests(List<String> testIds, boolean orderable) {
        List<Test> tests = new ArrayList<Test>();

        for( String testId : testIds){
            Test test = new TestService(testId).getTest();
            test.setOrderable( orderable );
            test.setSysUserId(currentUserId);
            tests.add(test);
        }

        return tests;
    }


    private List<String> getIdsForActions(String key, JSONObject root, JSONParser parser){
        List<String> list = new ArrayList<String>();

        String action = (String)root.get(key);

        try {
            JSONArray actionArray = (JSONArray)parser.parse(action);

            for(int i = 0 ; i < actionArray.size(); i++   ){
                list.add((String) ((JSONObject) actionArray.get(i)).get("id"));
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

}

