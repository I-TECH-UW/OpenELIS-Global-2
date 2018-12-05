/**
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
*/
package us.mn.state.health.lims.common.provider.validation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.servlet.validation.AjaxServlet;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class AccessionNumberValidationProvider extends BaseValidationProvider {

	public AccessionNumberValidationProvider() {
		super();
	}

	public AccessionNumberValidationProvider(AjaxServlet ajaxServlet) {
		this.ajaxServlet = ajaxServlet;
	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get id from request
		String targetId = request.getParameter("id");
		String formField = request.getParameter("field");
		String form = request.getParameter("form");
		String result = validate(targetId, form);
		ajaxServlet.sendData(formField, result, request, response);
	}

	// modified for efficiency bugzilla 1367
	/**
	 * validate() - for AccessionNumberValidationProvider
	 * 
	 * @param targetId - String
	 * @return String - valid or invalid
	 */
	public String validate(String targetId, String form) throws LIMSRuntimeException {
		String retVal = VALID;

		if (!StringUtil.isNullorNill(targetId)) {

			try {
				SampleDAO sampleDAO = new SampleDAOImpl();

				// Get sample by passing in sample vo instead of targetId.
				Sample sample = sampleDAO.getSampleByAccessionNumber(targetId.trim());
				
				// BGM - bugzilla 1495 now we need to know which form it's coming from
				// and need to read what is the correct status from SytemConfig
				if (sample == null) {
					retVal = INVALID;
				} else if( form != null) {
					if (form.equalsIgnoreCase("humanSampleOneForm")) {
						// bugzilla 1581 handle null status
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if(!sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusQuickEntryComplete()))
								retVal = INVALID;
						} else {
							retVal = INVALID;
						}
					} else if (form.equalsIgnoreCase("humanSampleTwoForm")) {
						// bugzilla 1581 handle null status
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if(!sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusEntry1Complete()))
								retVal = INVALID;
						} else {
							retVal = INVALID;
						}
					//bugzilla 2437 batch xml
				    //bugzilla 2472: fix bug
					} else if (form.equalsIgnoreCase("influenzaSampleXMLBySampleForm")) {
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if (!sample.getStatus().equals(
									SystemConfiguration.getInstance()
									.getSampleStatusEntry2Complete()) && !sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusReleased())) 
						
								retVal = INVALIDSTATUS;
							
						} else {
							retVal = INVALID;
						}
					} else if (form.equalsIgnoreCase("testManagementForm")) {
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if(sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusLabelPrinted()))
								retVal = INVALIDSTATUS;
						} else {
							retVal = INVALID;
						}
						// bugzilla 2050
						//bugzilla 2513
					} else if (form.equalsIgnoreCase("resultsEntryForm")) {
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if (sample.getStatus().equals(
									SystemConfiguration.getInstance()
									.getSampleStatusLabelPrinted())) 
								
								retVal = INVALIDSTATUS;
							
						} else {
							retVal = INVALID;
						}
					}
					//bugzilla 2513
					else if (form.equalsIgnoreCase("batchResultsVerificationForm")) {
						if (!StringUtil.isNullorNill(sample.getStatus())) {
	                        //for human (clinical) we require demographics to be available at time of results verification
							if(sample.getDomain() != null && sample.getDomain().equals(SystemConfiguration.getInstance().getHumanDomain()) && !sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusEntry2Complete()))
						    	retVal = INVALIDSTATUS;
							//for newborn (and possibly other domains) allow verification of sample results even after quick entry
							if (sample.getDomain() != null 
									&& !sample.getDomain().equals(SystemConfiguration.getInstance().getHumanDomain()) 
									&& sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusLabelPrinted())) 
								retVal = INVALIDSTATUS;
						} else {
							retVal = INVALID;
						}
						// bugzilla 2028
					} else if (form.equalsIgnoreCase("qaEventsEntryForm")) {
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if (sample.getStatus().equals(
									SystemConfiguration.getInstance()
									.getSampleStatusLabelPrinted()))
								retVal = INVALIDSTATUS;
						//bugzilla 2029
						} else if (form.equalsIgnoreCase("batchQaEventsEntryForm")) {
							if (!StringUtil.isNullorNill(sample.getStatus())) {
								if (sample.getStatus().equals(
										SystemConfiguration.getInstance()
										.getSampleStatusLabelPrinted()))
									retVal = INVALIDSTATUS;
								
							}							
						} else {
							retVal = INVALID;
						}
						//bugzilla 2529
					} else if (form.equalsIgnoreCase("newbornSampleOneForm")) {
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if(!sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusQuickEntryComplete()))
								retVal = INVALIDSTATUS;
						} else {
							retVal = INVALID;
						}
						//bugzilla 2530
					} else if (form.equalsIgnoreCase("newbornSampleTwoForm")) {
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if(!sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusEntry1Complete()))
								retVal = INVALIDSTATUS;
						} else {
							retVal = INVALID;
						}
						//bugzilla 2531
					} else if (form.equalsIgnoreCase("newbornSampleFullForm")) {
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if(!sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusEntry2Complete()))
								retVal = INVALIDSTATUS;
						} else {
							retVal = INVALID;
						}
						//bugzilla 2564
					} else if (form.equalsIgnoreCase("testManagementNewbornForm")) {
						if (!StringUtil.isNullorNill(sample.getStatus())) {
							if(!sample.getStatus().equals(SystemConfiguration.getInstance().getSampleStatusEntry2Complete()))
								retVal = INVALIDSTATUS;
						} else {
							retVal = INVALID;
						}						
					} else
						retVal = VALID;
				}

			} catch (NumberFormatException nFE) {
                //bugzilla 2154
			    LogEvent.logError("AccessionNumberValidationProvider","validate()",nFE.toString());
				retVal = INVALID;
			}

		} else {
			retVal = INVALID;
		}

		return retVal;
	}

}
