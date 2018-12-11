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
package us.mn.state.health.lims.common.provider.query;

import org.apache.commons.validator.GenericValidator;
import org.json.simple.JSONObject;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

public class TestEntitiesProvider extends BaseQueryProvider {

	protected AjaxServlet ajaxServlet = null;

    @Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String testId = request.getParameter("testId");

        String jResult;
        JSONObject jsonResult = new JSONObject();
        String jString;

		if (GenericValidator.isBlankOrNull(testId) ){
			jResult = INVALID;
			jString = "Internal error, please contact Admin and file bug report";
		} else {
            jResult = createJsonTestEntities( testId, jsonResult );
            StringWriter out = new StringWriter();
            try{
                jsonResult.writeJSONString( out );
                jString = out.toString();
            }catch( IOException e ){
                e.printStackTrace();
                jResult = INVALID;
                jString = "Internal error, please contact Admin and file bug report";
            }catch( IllegalStateException e ){
                e.printStackTrace();
                jResult = INVALID;
                jString = "Internal error, please contact Admin and file bug report";
            }
        }
		ajaxServlet.sendData(jString, jResult, request, response);

	}

    @SuppressWarnings("unchecked")
    private String createJsonTestEntities( String testId, JSONObject jsonResult )throws IllegalStateException{

        if( GenericValidator.isBlankOrNull( testId ) ){
            throw new IllegalStateException( "TestEntitiesProvider testId was blank.  It must have a value" );
        }

        Test test = new TestService( testId ).getTest();
        if( test != null){
            TestSection testSection = test.getTestSection();
            String testSectionId = "";
            UnitOfMeasure uom = test.getUnitOfMeasure();
            String uomId = "";

            if(testSection != null) { testSectionId = testSection.getId(); }
            if(uom != null) { uomId = uom.getId(); }
            String loinc = test.getLoinc();
            String isActive = test.getIsActive();
            Boolean orderable = test.getOrderable();
            
            JSONObject idObject = new JSONObject();
            idObject.put("testSectionId", testSectionId);
            idObject.put("uomId", uomId);
            idObject.put("loinc", loinc);
            idObject.put("isActive", isActive);
            idObject.put("orderable", orderable);
            jsonResult.put("entities", idObject);
     
            return VALID;
        }

        return INVALID;
    }

	@Override
	public void setServlet(AjaxServlet as) {
		this.ajaxServlet = as;
	}

	@Override
	public AjaxServlet getServlet() {
		return this.ajaxServlet;
	}

}
