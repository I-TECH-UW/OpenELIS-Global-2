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
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.common.provider.query;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.util.XMLUtil;
import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.ObservationHistoryTypeMap;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class ObservationHistoryPopulateProvider extends BaseQueryProvider {
    
    /**
     * nationality and nationalityOther are both fields stored as OH type = "nationality" one is stored as D and the "other" L
     */
    private static final String NATIONALITY_OBSERVATION_HISTORY = "nationality";
    private static final String NATIONALITY_SUFFIX = "Other";
    /**
     * priorDiseases and currentDiseases both have an additional field xDiseasesValue 
     */
    public static final String[] DISEASES_OTHER = {
        "priorDiseases",                
        "currentDiseases",              
    };
    private static final String DISEASES_SUFFIX = "Value";

    private ObservationHistoryTypeMap ohTypeMap = ObservationHistoryTypeMap.getInstance();
    /**
     * All of the following occur multiple time in observation history
     */
    public static final String[] MULTI_LISTS = {
        "priorARVTreatmentINNs",        
        "futureARVTreatmentINNs",       
        "arvTreatmentAdvEffGrd",        
        "arvTreatmentAdvEffType",       
        "cotrimoxazoleTreatAdvEffGrd",  
        "cotrimoxazoleTreatAdvEffType",
        "currentARVTreatmentINNs"
       // "initialSampleConditionINNs"
    };
    
    ObservationHistoryDAO ohDAO = new ObservationHistoryDAOImpl();

    StringBuilder xml = new StringBuilder();

    private Patient patient = new Patient();
    private Sample sample = new Sample();
    Map<String, String> historyMap = new LinkedHashMap<String, String>();
//    private String projectFormName;
//    private RequestType requestType = RequestType.UNKNOWN;
    
	@Override
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String patientKey = (String) request.getParameter("patientKey");
        String sampleKey = (String) request.getParameter("sampleKey");
        patient.setId(patientKey);
        sample.setId(sampleKey);
        String s = (String)request.getSession().getAttribute("type");
//        requestType = RequestType.valueOfAsUpperCase(s);

		String result = createSearchResultXML();
		ajaxServlet.sendData(xml.toString(), result, request, response);
	}

	private String createSearchResultXML() {
		String success = VALID;

		buildSingleObservationHistories();
		addMultiHistories();
//		projectFormName = historyMap.get("projectFormName");
		addDoubleHistories(NATIONALITY_OBSERVATION_HISTORY, NATIONALITY_SUFFIX);
		for (String disease : DISEASES_OTHER) {            
		    addDoubleHistories(disease, DISEASES_SUFFIX);
        }
		
		appendToXML();
		
		return success;
	}

    private void buildSingleObservationHistories() {
        List<ObservationHistory> histories;
		histories = ohDAO.getAll(patient, sample);
        for (ObservationHistory history : histories) {
            String ohTypeName = ohTypeMap.getTypeFromId(history.getObservationHistoryTypeId());
            if (ohTypeName.contains("RecordStatus")) {  // sample and patient record status get mapped to their localized strings.
                List<Dictionary> list = ObservationHistoryList.REC_STATUS.getList();
                for (Dictionary d : list) {
                    if (d.getId().equals(history.getValue())) {                        
                        historyMap.put(ohTypeName, d.getLocalizedName());
                    }
                }
            } else {
                historyMap.put(ohTypeName, history.getValue());
            }
        }
    }

    /**
     * This is for OH that just repeat multiple times. In the UI the have IDs XYZ0, XYZ1 etc.
     */
    private void addMultiHistories() { 
    	for (String multiListName : MULTI_LISTS) {
    	    historyMap.remove(multiListName);
    	    List<ObservationHistory> multiHistories = getObservationsByType(multiListName);
    	    if (multiHistories.size() > 0) {
    	    	
    	    	Collections.sort(multiHistories, new Comparator<ObservationHistory>() {
					@Override
					public int compare(ObservationHistory o1, ObservationHistory o2) {
						return o1.getId().compareTo(o2.getId());
					}
				});
    	    	
        	    for (int i = 0; i < multiHistories.size(); i++) {
                    historyMap.put(multiListName+i, multiHistories.get(i).getValue());
                }
    	    }
    	}
    }
	
	/**
	 * A few things are in the OH twice.  In the OH records there may be two "nationality" entries, 1 from a dictionary value for "Yes"
	 * and the 2nd specifies a string value.  
	 */
	private void addDoubleHistories(String doubleOHName, String suffix) {
	    // the code which loaded everything assuming it occured once probably added one of these, so delete that because we don't know which came last.
	    historyMap.remove(doubleOHName);
        List<ObservationHistory> histories = getObservationsByType(doubleOHName);
        for (ObservationHistory history : histories) {
            String code = history.getValueType();
            if (code.equals(ObservationHistory.ValueType.DICTIONARY.getCode())) {
                historyMap.put(doubleOHName, history.getValue());
            } else {
                historyMap.put(doubleOHName+suffix, history.getValue());
            }
        }
	}

    /**
     * Get all observations histories of a certain types
     * @param listName
     * @return
     */
	private List<ObservationHistory> getObservationsByType(String listName) {
        String typeId = ObservationHistoryTypeMap.getInstance().getIDForType(listName);
        List<ObservationHistory> multiHistories = ohDAO.getAll(patient, sample, typeId);
        return multiHistories;
    }
	
	private void appendToXML() {
	    Set<Entry<String, String>> entries = historyMap.entrySet();
	    for (Entry<String, String> entry : entries) {
            XMLUtil.appendKeyValue(entry.getKey(), entry.getValue(), xml);
        }
	}
}
