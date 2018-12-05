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
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.DisplayListService;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.unitofmeasure.dao.UnitOfMeasureDAO;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

public class UomRenameUpdate extends BaseAction{


    protected ActionForward performAction( ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response ) throws Exception{

        String forward = FWD_SUCCESS;

        BaseActionForm dynaForm = ( BaseActionForm ) form;
        String unitOfMeasureId = dynaForm.getString( "uomId" );
        String nameEnglish = dynaForm.getString( "nameEnglish" );
        String nameFrench = dynaForm.getString( "nameFrench" );
        String userId = getSysUserId( request );	

        updateUnitOfMeasureNames(unitOfMeasureId, nameEnglish, nameFrench, userId);

        //Refresh testSection names
        DisplayListService.getFreshList( DisplayListService.ListType.UNIT_OF_MEASURE );
        return mapping.findForward( forward );
    }

    private void updateUnitOfMeasureNames(String unitOfMeasureId, String nameEnglish, String nameFrench, String userId){
    	UnitOfMeasureDAO unitOfMeasureDAO = new UnitOfMeasureDAOImpl();
    	UnitOfMeasure unitOfMeasure = unitOfMeasureDAO.getUnitOfMeasureById(unitOfMeasureId);
    	
    	

        if( unitOfMeasure != null ){
        	
        	// not using localization for UOM
        	
//            Localization name = unitOfMeasure.getLocalization();
//
//            name.setEnglish( nameEnglish.trim() );
//            name.setFrench( nameFrench.trim() );
//            name.setSysUserId( userId );
        	
        	unitOfMeasure.setUnitOfMeasureName(nameEnglish.trim() );
        	unitOfMeasure.setSysUserId(userId);

            Transaction tx = HibernateUtil.getSession().beginTransaction();

            try{
//              new LocalizationDAOImpl().updateData( name );
                //new
            	unitOfMeasureDAO.updateData(unitOfMeasure);

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


