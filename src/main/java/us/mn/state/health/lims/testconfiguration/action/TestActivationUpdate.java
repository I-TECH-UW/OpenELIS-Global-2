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
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;


public class TestActivationUpdate extends BaseAction {

    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String changeList = ((DynaValidatorForm)form).getString("jsonChangeList");

        JSONParser parser=new JSONParser();

        JSONObject obj = (JSONObject)parser.parse(changeList);

        List<ActivateSet> activateSampleSets = getActivateSetForActions("activateSample", obj, parser);
        List<String> deactivateSampleIds = getIdsForActions("deactivateSample", obj, parser);
        List<ActivateSet> activateTestSets = getActivateSetForActions("activateTest", obj, parser);
        List<String> deactivateTestIds = getIdsForActions("deactivateTest", obj, parser);

        List<Test> deactivateTests = getDeactivatedTests(deactivateTestIds);
        List<Test> activateTests = getActivatedTests(activateTestSets);
        List<TypeOfSample> deactivateSampleTypes = getDeactivatedSampleTypes(deactivateSampleIds );
        List<TypeOfSample> activateSampleTypes = getActivatedSampleTypes(activateSampleSets);

        Transaction tx = HibernateUtil.getSession().beginTransaction();

        TestDAO testDAO = new TestDAOImpl();
        TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();

        try{
            for(Test test : deactivateTests){
                testDAO.updateData(test);
            }

            for(Test test : activateTests){
                testDAO.updateData(test);
            }

            for(TypeOfSample typeOfSample : deactivateSampleTypes){
                typeOfSampleDAO.updateData(typeOfSample);
            }

            for(TypeOfSample typeOfSample : activateSampleTypes){
                typeOfSampleDAO.updateData(typeOfSample);
            }

            if( !deactivateSampleTypes.isEmpty() || !activateSampleTypes.isEmpty()){
                TypeOfSampleService.clearCache();
            }

            tx.commit();
        }catch( HibernateException e ){
            tx.rollback();
        }finally{
            HibernateUtil.closeSession();
        }

        DisplayListService.refreshList(DisplayListService.ListType.SAMPLE_TYPE_ACTIVE);
        DisplayListService.refreshList(DisplayListService.ListType.SAMPLE_TYPE_INACTIVE);
        TypeOfSampleService.clearCache();

        return mapping.findForward(FWD_SUCCESS);
    }



    private List<Test> getDeactivatedTests(List<String> testIds) {
        List<Test> tests = new ArrayList<Test>();

        for( String testId : testIds){
            Test test = new TestService(testId).getTest();
            test.setIsActive( "N");
            test.setSysUserId(currentUserId);
            tests.add(test);
        }

        return tests;
    }

    private List<Test> getActivatedTests(List<ActivateSet> testIds) {
        List<Test> tests = new ArrayList<Test>();

        for( ActivateSet set : testIds){
            Test test = new TestService(set.id).getTest();
            test.setIsActive( "Y");
            test.setSortOrder( String.valueOf(set.sortOrder * 10));
            test.setSysUserId(currentUserId);
            tests.add(test);
        }

        return tests;
    }

    private List<TypeOfSample> getDeactivatedSampleTypes(List<String> sampleTypeIds) {
        List<TypeOfSample> sampleTypes = new ArrayList<TypeOfSample>();

        for( String id : sampleTypeIds){
            TypeOfSample typeOfSample = TypeOfSampleService.getTransientTypeOfSampleById(id);
            typeOfSample.setActive( false );
            typeOfSample.setSysUserId(currentUserId);
            sampleTypes.add(typeOfSample);
        }

        return sampleTypes;
    }

    private List<TypeOfSample> getActivatedSampleTypes(List<ActivateSet> sampleTypeSets) {
        List<TypeOfSample> sampleTypes = new ArrayList<TypeOfSample>();

        for( ActivateSet set : sampleTypeSets){
            TypeOfSample typeOfSample = TypeOfSampleService.getTransientTypeOfSampleById(set.id);
            typeOfSample.setActive( true );
            typeOfSample.setSortOrder(set.sortOrder * 10);
            typeOfSample.setSysUserId(currentUserId);
            sampleTypes.add(typeOfSample);
        }

        return sampleTypes;
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

    private List<ActivateSet> getActivateSetForActions(String key, JSONObject root, JSONParser parser) {
        List<ActivateSet> list = new ArrayList<ActivateSet>();

        String action = (String)root.get(key);

        try {
            JSONArray actionArray = (JSONArray)parser.parse(action);

            for(int i = 0 ; i < actionArray.size(); i++   ){
                ActivateSet set = new ActivateSet();
                set.id = String.valueOf(((JSONObject) actionArray.get(i)).get("id"));
                Long longSort = (Long)((JSONObject) actionArray.get(i)).get("sortOrder");
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

    private class ActivateSet{
        public String id;
        public Integer sortOrder;
    }
}

