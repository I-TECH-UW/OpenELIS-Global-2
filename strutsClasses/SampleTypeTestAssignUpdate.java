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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleTestDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;


public class SampleTypeTestAssignUpdate extends BaseAction {
    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaValidatorForm dynaForm = (DynaValidatorForm)form;
        String testId = dynaForm.getString("testId");
        String sampleTypeId = dynaForm.getString("sampleTypeId");
        String deactivateSampleTypeId = dynaForm.getString("deactivateSampleTypeId");
        boolean updateTypeOfSample = false;
        String currentUser = getSysUserId(request);
        
        TypeOfSample typeOfSample = TypeOfSampleService.getTransientTypeOfSampleById(sampleTypeId);
        TypeOfSample deActivateTypeOfSample = null;
        
        //Test test = new TestService(testId).getTest();
        

        //This covers the case that they are moving the test to the same sample type they are moving it from
        if(sampleTypeId.equals(deactivateSampleTypeId)){
            return mapping.findForward(FWD_SUCCESS);
        }

        TypeOfSampleTest typeOfSampleTestOld = new TypeOfSampleTestDAOImpl().getTypeOfSampleTestForTest(testId);
        boolean deleteExistingTypeOfSampleTest = false;
        String[] typeOfSamplesTestIDs = new String[1];
        
        if (typeOfSampleTestOld != null) {       	
        	typeOfSamplesTestIDs[0] = typeOfSampleTestOld.getId();
        	deleteExistingTypeOfSampleTest = true;
        }
    //---------------------------    
       /* if( "N".equals(typeOfSample.getIsActive())){
        	typeOfSample.setIsActive(true);
        	typeOfSample.setSysUserId(currentUser);
        	updateTypeOfSample = true;
        }*/
        
        
      // Boolean value = false; 
        if( typeOfSample.getIsActive()== false){
        	typeOfSample.setIsActive(true);
        	typeOfSample.setSysUserId(currentUser);
        	updateTypeOfSample = true;
        }
        
        
        
//------------------------------------------
        if( !GenericValidator.isBlankOrNull(deactivateSampleTypeId) ){
        	deActivateTypeOfSample  = TypeOfSampleService.getTransientTypeOfSampleById(deactivateSampleTypeId);
        	deActivateTypeOfSample.setIsActive(false);
        	deActivateTypeOfSample.setSysUserId(currentUser);
        }

        Transaction tx = HibernateUtil.getSession().beginTransaction();
        try {
        	if (deleteExistingTypeOfSampleTest) {
        		new TypeOfSampleTestDAOImpl().deleteData(typeOfSamplesTestIDs, currentUser);
        	}

            if(updateTypeOfSample){
                new TypeOfSampleDAOImpl().updateData(typeOfSample);
            }            
            
            TypeOfSampleTest typeOfSampleTest = new TypeOfSampleTest();
            typeOfSampleTest.setTestId(testId);
            typeOfSampleTest.setTypeOfSampleId(sampleTypeId);
            typeOfSampleTest.setSysUserId(currentUser);
            typeOfSampleTest.setLastupdatedFields();
            
            new TypeOfSampleTestDAOImpl().insertData(typeOfSampleTest);
        
            if( deActivateTypeOfSample != null){
                new TypeOfSampleDAOImpl().updateData(deActivateTypeOfSample);
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

        DisplayListService.refreshList(DisplayListService.ListType.SAMPLE_TYPE);
        DisplayListService.refreshList(DisplayListService.ListType.SAMPLE_TYPE_INACTIVE);

        return mapping.findForward(FWD_SUCCESS);
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
