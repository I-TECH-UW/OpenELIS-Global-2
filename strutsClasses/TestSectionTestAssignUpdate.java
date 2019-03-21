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
import us.mn.state.health.lims.common.services.TestSectionService;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;


public class TestSectionTestAssignUpdate extends BaseAction {
    @Override
    protected ActionForward performAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaValidatorForm dynaForm = (DynaValidatorForm)form;
        String testId = dynaForm.getString("testId");
        String testSectionId = dynaForm.getString("testSectionId");
        String deactivateTestSectionId = dynaForm.getString("deactivateTestSectionId");
        boolean updateTestSection = false;
        String currentUser = getSysUserId(request);
        Test test = new TestService(testId).getTest();
        TestSection testSection = new TestSectionService(testSectionId).getTestSection();
        TestSection deActivateTestSection = null;
        test.setTestSection(testSection);
        test.setSysUserId(currentUser);

        //This covers the case that they are moving the test to the same test section they are moving it from
        if(testSectionId.equals(deactivateTestSectionId)){
            return mapping.findForward(FWD_SUCCESS);
        }

        if( "N".equals(testSection.getIsActive())){
            testSection.setIsActive("Y");
            testSection.setSysUserId(currentUser);
            updateTestSection = true;
        }

        if( !GenericValidator.isBlankOrNull(deactivateTestSectionId) ){
            deActivateTestSection  = new TestSectionService(deactivateTestSectionId).getTestSection();
            deActivateTestSection.setIsActive("N");
            deActivateTestSection.setSysUserId(currentUser);
        }

        Transaction tx = HibernateUtil.getSession().beginTransaction();
        try {
            new TestDAOImpl().updateData(test);

            if(updateTestSection){
                new TestSectionDAOImpl().updateData(testSection);
            }

            if( deActivateTestSection != null){
                new TestSectionDAOImpl().updateData(deActivateTestSection);
            }
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            HibernateUtil.closeSession();
        }

        DisplayListService.refreshList(DisplayListService.ListType.TEST_SECTION);
        DisplayListService.refreshList(DisplayListService.ListType.TEST_SECTION_INACTIVE);

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
