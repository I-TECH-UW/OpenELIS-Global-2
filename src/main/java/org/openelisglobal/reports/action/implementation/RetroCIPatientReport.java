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
package org.openelisglobal.reports.action.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.services.IStatusService;
import org.openelisglobal.common.services.StatusService.AnalysisStatus;
import org.openelisglobal.common.services.StatusService.OrderStatus;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.reports.action.implementation.reportBeans.ErrorMessages;
import org.openelisglobal.reports.form.ReportForm;
import org.openelisglobal.sample.service.SampleService;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.spring.util.SpringContext;

public abstract class RetroCIPatientReport extends RetroCIReport {

    protected static String ANALYSIS_FINALIZED_STATUS_ID;

    protected static List<Integer> READY_FOR_REPORT_STATUS_IDS;
    protected Patient reportPatient;
    protected Sample reportSample;

    private static ObservationHistoryService observationService = SpringContext
            .getBean(ObservationHistoryService.class);
    private SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);
    private SampleService sampleService = SpringContext.getBean(SampleService.class);

    private String lowerNumber;
    private String upperNumber;
    private List<String> handledOrders;

    static {
        READY_FOR_REPORT_STATUS_IDS = new ArrayList<>();
        READY_FOR_REPORT_STATUS_IDS
                .add(Integer.parseInt(SpringContext.getBean(IStatusService.class).getStatusID(OrderStatus.Finished)));
        READY_FOR_REPORT_STATUS_IDS.add(Integer.parseInt(SpringContext.getBean(IStatusService.class).getStatusID(OrderStatus.Started)));

        ANALYSIS_FINALIZED_STATUS_ID = SpringContext.getBean(IStatusService.class).getStatusID(AnalysisStatus.Finalized);
    }

    @Override
    public void initializeReport(ReportForm form) {
        super.initializeReport();
        errorFound = false;

        lowerNumber = form.getAccessionDirect();
        upperNumber = form.getHighAccessionDirect();

        handledOrders = new ArrayList<>();

        createReportParameters();

        boolean valid = validateAccessionNumbers();

        if (valid) {
            List<Sample> reportSampleList = findReportSamples(lowerNumber, upperNumber);

            if (reportSampleList.isEmpty()) {
                errorFound = true;
                ErrorMessages msgs = new ErrorMessages();
                msgs.setMsgLine1(MessageUtil.getMessage("report.error.message.noPrintableItems"));
                errorMsgs.add(msgs);
            }

            Collections.sort(reportSampleList, new Comparator<Sample>() {
                @Override
                public int compare(Sample o1, Sample o2) {
                    return o1.getAccessionNumber().compareTo(o2.getAccessionNumber());
                }
            });

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
     * Until the ARV study has a initial and followup project we have to let each
     * study figure out which one the patient is in
     */
    protected boolean allowSample() {
        return true;
    }

    private List<Sample> findReportSamples(String lowerNumber, String upperNumber) {
        return sampleService.getSamplesByProjectAndStatusIDAndAccessionRange(getProjIdsList(getProjectId()),
                READY_FOR_REPORT_STATUS_IDS, lowerNumber, upperNumber);
    }

    protected abstract String getProjectId();

    protected abstract void initializeReportItems();

    protected abstract void createReportItems();

    protected void findPatientFromSample() {
        reportPatient = sampleHumanService.getPatientForSample(reportSample);
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
        List<ObservationHistory> observationList = observationService.getAll(reportPatient, reportSample,
                observationTypeId);
        return observationList.size() > 0 ? observationList.get(0).getValue() : "";
    }

    @Override
    public List<String> getReportedOrders() {
        return handledOrders;
    }

    protected List<Integer> getProjIdsList(String projID) {

        String[] fields = projID.split(":");
        List<Integer> projIDList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            projIDList.add(Integer.parseInt(fields[i]));
        }
        return projIDList;
    }
}
