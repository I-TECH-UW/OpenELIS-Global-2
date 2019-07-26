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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import org.openelisglobal.common.action.BaseAction;
import org.openelisglobal.common.action.BaseActionForm;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.TestService;
import org.openelisglobal.hibernate.HibernateUtil;
import org.openelisglobal.localization.daoimpl.LocalizationDAOImpl;
import org.openelisglobal.localization.valueholder.Localization;
import org.openelisglobal.test.valueholder.Test;

/**
 * @author diane benz
 *         <p/>
 *         To change this generated comment edit the template variable "typecomment":
 *         Window>Preferences>Java>Templates. To enable and disable the creation of type
 *         comments go to Window>Preferences>Java>Code Generation.
 */
public class TestRenameUpdate extends BaseAction{

    protected ActionForward performAction( ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response ) throws Exception{

        String forward = FWD_SUCCESS;

        BaseActionForm dynaForm = ( BaseActionForm ) form;
        String testId = dynaForm.getString( "testId" );
        String nameEnglish = dynaForm.getString( "nameEnglish" );
        String nameFrench = dynaForm.getString( "nameFrench" );
        String reportNameEnglish = dynaForm.getString( "reportNameEnglish" );
        String reportNameFrench = dynaForm.getString( "reportNameFrench" );
        String userId = getSysUserId( request );

        updateTestNames( testId, nameEnglish, nameFrench, reportNameEnglish, reportNameFrench, userId );

        return mapping.findForward( forward );
    }

    private void updateTestNames( String testId, String nameEnglish, String nameFrench, String reportNameEnglish, String reportNameFrench, String userId ){
        Test test = new TestService( testId ).getTest();

        if( test != null ){

            Localization name = test.getLocalizedTestName();
            Localization reportingName = test.getLocalizedReportingName();
            name.setEnglish( nameEnglish.trim() );
            name.setFrench( nameFrench.trim() );
            name.setSysUserId( userId );
            reportingName.setEnglish( reportNameEnglish.trim() );
            reportingName.setFrench( reportNameFrench.trim() );
            reportingName.setSysUserId( userId );

            Transaction tx = HibernateUtil.getSession().beginTransaction();

            try{
                new LocalizationDAOImpl().updateData( name );
                new LocalizationDAOImpl().updateData( reportingName );
                tx.commit();
            }catch( HibernateException e ){
                tx.rollback();
            }finally{
                HibernateUtil.closeSession();
            }


        }

        //Refresh test names
        DisplayListService.getInstance().getFreshList( DisplayListService.ListType.ALL_TESTS );
        DisplayListService.getInstance().getFreshList( DisplayListService.ListType.ORDERABLE_TESTS );
    }

    protected String getPageTitleKey(){
        return "";
    }

    protected String getPageSubtitleKey(){
        return "";
    }

}
