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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.json.simple.JSONObject;

import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.renametestsection.daoimpl.RenameTestSectionDAOImpl;
import us.mn.state.health.lims.renametestsection.valueholder.RenameTestSection;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

public class EntityNamesProvider extends BaseQueryProvider {
    public static final String PANEL = "panel";
    public static final String SAMPLE_TYPE = "sampleType";
    public static final String TEST_SECTION = "testSection";
    public static final String UNIT_OF_MEASURE = "unitOfMeasure";
	protected AjaxServlet ajaxServlet = null;

    @Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("entityId");
        String entityName = request.getParameter("entityName");

        String jResult;
        JSONObject jsonResult = new JSONObject();
        String jString;

		if (GenericValidator.isBlankOrNull(id) || GenericValidator.isBlankOrNull(entityName)){
			jResult = INVALID;
			jString = "Internal error, please contact Admin and file bug report";
		} else {
            jResult = createJsonTestNames( id, entityName, jsonResult );
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
    private String createJsonTestNames(String id, String entityName, JSONObject jsonResult)throws IllegalStateException{

        Localization localization = null;

        if( PANEL.equals(entityName)){
            localization = getLocalizationForPanel(id);
        }else if(SAMPLE_TYPE.equals(entityName)){
            localization = getLocalizationForSampleType(id);
        }else if(TEST_SECTION.equals(entityName)){
            localization = getLocalizationForRenameTestSection(id);
        }else if(UNIT_OF_MEASURE.equals(entityName)){
            localization = getLocalizationForUnitOfMeasure(id);
        }//add entity types as needed

        if( localization != null){

            JSONObject nameObject = new JSONObject();
            nameObject.put("english", localization.getEnglish());
            nameObject.put("french", localization.getFrench());
            jsonResult.put("name", nameObject);

            return VALID;
        }

        return INVALID;
    }

    private Localization getLocalizationForPanel(String id) {
        Panel panel = new PanelDAOImpl().getPanelById( id );
        return panel != null ? panel.getLocalization() : null;
    }

    private Localization getLocalizationForSampleType(String id) {
        TypeOfSample typeOfSample = new TypeOfSampleDAOImpl().getTypeOfSampleById(id);
        return typeOfSample != null ? typeOfSample.getLocalization() : null;
    }
  
    private Localization getLocalizationForRenameTestSection(String id) {
        RenameTestSection testSection = new RenameTestSectionDAOImpl().getTestSectionById(id);
        return testSection != null ? testSection.getLocalization() : null;
    }
    
    private Localization getLocalizationForUnitOfMeasure(String id) {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasureDAOImpl().getUnitOfMeasureById(id);
        return unitOfMeasure != null ? unitOfMeasure.getLocalization() : null;
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
