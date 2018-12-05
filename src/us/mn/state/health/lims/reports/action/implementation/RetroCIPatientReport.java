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
 * Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.reports.action.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ErrorMessages;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.dao.SampleHumanDAO;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;

public abstract class RetroCIPatientReport extends RetroCIReport {

	protected static String ANALYSIS_FINALIZED_STATUS_ID;

	protected static List<Integer> READY_FOR_REPORT_STATUS_IDS;
	private static ObservationHistoryDAO observationDAO = new ObservationHistoryDAOImpl();
	protected Patient reportPatient;
	protected Sample reportSample;
	private SampleHumanDAO sampleHumanDAO = new SampleHumanDAOImpl();
	private String lowerNumber;
	private String upperNumber;
	private List<String> handledOrders;

	static{
		READY_FOR_REPORT_STATUS_IDS = new ArrayList<Integer>();
		READY_FOR_REPORT_STATUS_IDS.add(Integer.parseInt(StatusService.getInstance().getStatusID(OrderStatus.Finished)));
		READY_FOR_REPORT_STATUS_IDS.add(Integer.parseInt(StatusService.getInstance().getStatusID(OrderStatus.Started)));

		ANALYSIS_FINALIZED_STATUS_ID = StatusService.getInstance().getStatusID(AnalysisStatus.Finalized);
	}


	@Override
	public void initializeReport(BaseActionForm dynaForm) {
		super.initializeReport();
		errorFound = false;

		lowerNumber = dynaForm.getString("accessionDirect");
		upperNumber = dynaForm.getString("highAccessionDirect");

		handledOrders = new ArrayList<String>();
		
		createReportParameters();

		boolean valid = validateAccessionNumbers();

		if (valid) {
			List<Sample> reportSampleList = findReportSamples(lowerNumber, upperNumber);

			if (reportSampleList.isEmpty()) {
				errorFound = true;
				ErrorMessages msgs = new ErrorMessages();
				msgs.setMsgLine1(StringUtil.getMessageForKey("report.error.message.noPrintableItems"));
				errorMsgs.add(msgs);
			}

			Collections.sort(reportSampleList, new Comparator<Sample>(){
				@Override
				public int compare(Sample o1, Sample o2) {
					return o1.getAccessionNumber().compareTo(o2.getAccessionNumber());
				}});
			
			initializeReportItems();

			for (Sample sample : reportSampleList) {
				handledOrders.add(sample.getId());
				reportSample = sample;
				findPatientFromSample();
				if (allowSample()) {
					createReportItems();
				}
			}
		}
	}

	private boolean validateAccessionNumbers() {

		if (GenericValidator.isBlankOrNull(lowerNumber) && GenericValidator.isBlankOrNull(upperNumber)) {
		    add1LineErrorMessage("report.error.message.noParameters");
			return false;
		}

		if (GenericValidator.isBlankOrNull(lowerNumber)) {
			lowerNumber = upperNumber;
		} else if (GenericValidator.isBlankOrNull(upperNumber)) {
			upperNumber = lowerNumber;
		}

		int lowIndex = findFirstNumber(lowerNumber);
		int highIndex = findFirstNumber(upperNumber);

		if (lowIndex == lowerNumber.length() || highIndex == upperNumber.length()) {
		    add1LineErrorMessage("report.error.message.noParameters");
			return false;
		}

		String lowPrefix = (String) lowerNumber.subSequence(0, lowIndex);
		String highPrefix = (String) upperNumber.subSequence(0, highIndex);

		if (!lowPrefix.equals(highPrefix)) {
			add1LineErrorMessage("report.error.message.samePrefix");
			return false;
		}

		double lowBounds = Double.parseDouble(lowerNumber.substring(lowIndex));
		double highBounds = Double.parseDouble(upperNumber.substring(highIndex));

		if (highBounds < lowBounds) {
			String temp = upperNumber;
			upperNumber = lowerNumber;
			lowerNumber = temp;
		}

		return true;
	}

	/*
	 * Until the ARV study has a initial and followup project we have to let
	 * each study figure out which one the patient is in
	 */
	protected boolean allowSample() {
		return true;
	}

	private List<Sample> findReportSamples(String lowerNumber, String upperNumber) {
		SampleDAO sampleDAO = new SampleDAOImpl();
		return sampleDAO.getSamplesByProjectAndStatusIDAndAccessionRange(getProjIdsList(getProjectId()), READY_FOR_REPORT_STATUS_IDS, lowerNumber,
				upperNumber);
	}

	protected abstract String getProjectId();

	protected abstract void initializeReportItems();

	protected abstract void createReportItems();

	protected void findPatientFromSample() {
		reportPatient = sampleHumanDAO.getPatientForSample(reportSample);
	}

	@Override
	protected void createReportParameters() {
	    super.createReportParameters();
		reportParameters.put("studyName", getReportNameForReport());
	}

	protected abstract String getReportNameForReport();

	private int findFirstNumber(String number) {
		for (int i = 0; i < number.length(); i++) {
			if (Character.isDigit(number.charAt(i))) {
				return i;
			}
		}
		return number.length();
	}

	protected String getObservationValues(String observationTypeId) {
		List<ObservationHistory> observationList = observationDAO.getAll(reportPatient, reportSample, observationTypeId);
		return observationList.size() > 0 ? observationList.get(0).getValue() : "";
	}
	
	@Override
	public List<String> getReportedOrders(){
	    	return handledOrders;
	}
	
    protected List<Integer> getProjIdsList(String projID){
		
		String[] fields = projID.split(":");
		List<Integer> projIDList= new ArrayList<Integer>();
		for (int i=0;i<fields.length;i++){
			projIDList.add(Integer.parseInt(fields[i]));
		}	
		return projIDList;
	}
}
