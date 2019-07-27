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
import org.openelisglobal.hibernate.HibernateUtil;
import org.openelisglobal.localization.daoimpl.LocalizationDAOImpl;
import org.openelisglobal.localization.valueholder.Localization;
import org.openelisglobal.panel.dao.PanelDAO;
import org.openelisglobal.panel.daoimpl.PanelDAOImpl;
import org.openelisglobal.panel.valueholder.Panel;

public class PanelRenameUpdate extends BaseAction{


    protected ActionForward performAction( ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response ) throws Exception{

        String forward = FWD_SUCCESS;

        BaseActionForm dynaForm = ( BaseActionForm ) form;
        String panelId = dynaForm.getString( "panelId" );
        String nameEnglish = dynaForm.getString( "nameEnglish" );
        String nameFrench = dynaForm.getString( "nameFrench" );
        String userId = getSysUserId( request );

        updatePanelNames(panelId, nameEnglish, nameFrench, userId);

        //Refresh panel names
        DisplayListService.getInstance().getFreshList( DisplayListService.ListType.PANELS );
        return mapping.findForward( forward );
    }

    private void updatePanelNames(String panelId, String nameEnglish, String nameFrench, String userId){
        PanelDAO panelDAO = new PanelDAOImpl();
        Panel panel = panelDAO.getPanelById(panelId);

        if( panel != null ){

            Localization name = panel.getLocalization();

            name.setEnglish( nameEnglish.trim() );
            name.setFrench( nameFrench.trim() );
            name.setSysUserId( userId );

            Transaction tx = HibernateUtil.getSession().beginTransaction();

            try{
                new LocalizationDAOImpl().updateData( name );

                tx.commit();
            }catch( HibernateException e ){
                tx.rollback();
            }finally{
                HibernateUtil.closeSession();
            }

        }
    }

    protected String getPageTitleKey(){
        return "";
    }

    protected String getPageSubtitleKey(){
        return "";
    }

}
