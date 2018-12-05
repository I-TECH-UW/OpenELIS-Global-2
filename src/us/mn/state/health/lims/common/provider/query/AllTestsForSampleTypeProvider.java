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

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import us.mn.state.health.lims.common.services.LocalizationService;
import us.mn.state.health.lims.common.services.TypeOfSampleService;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.test.valueholder.Test;

public class AllTestsForSampleTypeProvider extends BaseQueryProvider {

	protected AjaxServlet ajaxServlet = null;

    @Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sampleTypeId = request.getParameter("sampleTypeId");

        String jResult;
        JSONObject jsonResult = new JSONObject();
        String jString;

		if (GenericValidator.isBlankOrNull(sampleTypeId) ){
			jResult = INVALID;
			jString = "Internal error, please contact Admin and file bug report";
		} else {
            jResult = createJsonGroupedTestNames(sampleTypeId, jsonResult);
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
    private String createJsonGroupedTestNames(String sampleTypeId, JSONObject jsonResult)throws IllegalStateException{
        List<Test> tests = TypeOfSampleService.getAllTestsBySampleTypeId(sampleTypeId);

        JSONArray testArray = new JSONArray();

        for(Test test:tests){
            JSONObject testObject = new JSONObject();
            testObject.put("name" , LocalizationService.getLocalizedValue(test.getLocalizedTestName()));
            testObject.put("id", test.getId());
            testObject.put("isActive", test.getIsActive());
            testArray.add(testObject);
        }
        jsonResult.put("tests", testArray);
        return VALID;
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
