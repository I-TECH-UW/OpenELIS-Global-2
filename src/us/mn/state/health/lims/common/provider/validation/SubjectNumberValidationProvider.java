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
package us.mn.state.health.lims.common.provider.validation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.provider.query.PatientSearchResults;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.sample.daoimpl.SearchResultsDAOImp;

/**
 * The QuickEntryAccessionNumberValidationProvider class is used to validate,
 * via AJAX.
 */
public class SubjectNumberValidationProvider extends BaseValidationProvider{

    public SubjectNumberValidationProvider(){
        super();
    }

    public SubjectNumberValidationProvider( AjaxServlet ajaxServlet ){
        this.ajaxServlet = ajaxServlet;
    }

    @Override
    public void processRequest( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

        String queryResponse = "valid";
        String fieldId = request.getParameter( "fieldId" );
        String number = request.getParameter( "subjectNumber" );
        String numberType = request.getParameter( "numberType" );
        String STNumber = numberType.equals( "STnumber" ) ? number : null;
        String subjectNumber = numberType.equals( "subjectNumber" ) ? number : null;
        String nationalId = numberType.equals( "nationalId" ) ? number : null;


        //We just care about duplicates but blank values do not count as duplicates
        if( !( GenericValidator.isBlankOrNull( STNumber ) && GenericValidator.isBlankOrNull( subjectNumber ) && GenericValidator.isBlankOrNull( nationalId ) ) ){
            List<PatientSearchResults> results = new SearchResultsDAOImp().getSearchResults( null, null, STNumber, subjectNumber, nationalId, null, null, null );


            boolean allowDuplicates = ConfigurationProperties.getInstance().isPropertyValueEqual( ConfigurationProperties.Property.ALLOW_DUPLICATE_SUBJECT_NUMBERS, "true" );
            if( !results.isEmpty() ){
                queryResponse = ( allowDuplicates ? "warning#" + StringUtil.getMessageForKey("alert.warning") : "fail#" + StringUtil.getMessageForKey("alert.error") ) + ": " + StringUtil.getMessageForKey( "error.duplicate.subjectNumber.warning");
            }
        }
        response.setCharacterEncoding( "UTF-8" );
        ajaxServlet.sendData( fieldId, queryResponse, request, response );
    }
}
