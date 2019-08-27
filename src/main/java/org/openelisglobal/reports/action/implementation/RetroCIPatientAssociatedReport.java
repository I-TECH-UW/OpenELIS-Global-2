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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package org.openelisglobal.reports.action.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.services.QAService;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.observationhistory.service.ObservationHistoryService;
import org.openelisglobal.observationhistory.valueholder.ObservationHistory;
import org.openelisglobal.observationhistorytype.ObservationHistoryTypeMap;
import org.openelisglobal.patient.valueholder.Patient;
import org.openelisglobal.sample.util.CI.BaseProjectFormMapper;
import org.openelisglobal.sample.valueholder.Sample;
import org.openelisglobal.samplehuman.service.SampleHumanService;
import org.openelisglobal.spring.util.SpringContext;

public class RetroCIPatientAssociatedReport extends CollectionReport implements IReportParameterSetter {

    private ObservationHistoryService ohService = SpringContext.getBean(ObservationHistoryService.class);
    private SampleHumanService sampleHumanService = SpringContext.getBean(SampleHumanService.class);

    @Override
    public void setRequestParameters(BaseForm form) {
        try {
            PropertyUtils.setProperty(form, "reportName", MessageUtil.getMessage("patient.report.associated.name"));
            PropertyUtils.setProperty(form, "usePatientNumberDirect", Boolean.TRUE);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<byte[]> generateReports() {
        List<byte[]> byteList = new ArrayList<>();

        Patient patient = getPatient();

        if (patient != null) {
            String formNameId = ObservationHistoryTypeMap.getInstance().getIDForType("projectFormName");
            List<Sample> samples = sampleHumanService.getSamplesForPatient(patient.getId());

            for (Sample sample : samples) {
                List<ObservationHistory> projects = ohService.getAll(patient, sample, formNameId);
                System.out.println("\n" + sample.getAccessionNumber());
                if (!projects.isEmpty()) {
                    setProperty("accessionDirect", sample.getAccessionNumber());

                    if ("InitialARV_Id".equals(projects.get(0).getValue())) {
                        byteList.add(createReport("patientARVInitial1"));
                        byteList.add(createReport("patientARVInitial2"));
                    } else if ("FollowUpARV_Id".equals(projects.get(0).getValue())) {
                        byteList.add(createReport("patientARVFollowup1"));
                        byteList.add(createReport("patientARVFollowup2"));
                    } else if ("RTN_Id".equals(projects.get(0).getValue())) {
                        // no-op
                    } else if ("EID_Id".equals(projects.get(0).getValue())) {
                        byteList.add(createReport("patientEID1"));
                        byteList.add(createReport("patientEID2"));
                    } else if ("Indeterminate_Id".equals(projects.get(0).getValue())) {
                        byteList.add(createReport("patientIndeterminate1"));
                        byteList.add(createReport("patientIndeterminate2"));
                    } else if ("Special_Request_Id".equals(projects.get(0).getValue())) {
                        byteList.add(createReport("patientSpecialReport"));
                    }
                }

                if (QAService.isOrderNonConforming(sample)) {
                    setProperty("lowerDateRange", sample.getReceivedDateForDisplay());
                    byteList.add(createReport("retroCINonConformityByDate"));
                }

                if (isUnderInvestigation(sample)) {
                    setProperty("lowerDateRange", sample.getReceivedDateForDisplay());
                    byteList.add(createReport("retroCIFollowupRequiredByLocation"));
                }
            }
        }
        return byteList;
    }

    private void setProperty(String key, String value) {
        try {
            PropertyUtils.setProperty(form, key, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private boolean isUnderInvestigation(Sample sample) {
        String entryUnderInvestigationQuestion = getOptionalObservationHistory(sample,
                ObservationHistoryTypeMap.getInstance().getIDForType("underInvestigation"));
        return BaseProjectFormMapper.YES_ANSWERS.contains(entryUnderInvestigationQuestion);
    }

    private String getOptionalObservationHistory(Sample sample, String ohTypeId) {
        List<ObservationHistory> oh = ohService.getAll(null, sample, ohTypeId);
        if (oh == null || oh.size() == 0) {
            return null;
        }
        return oh.get(0).getValue();
    }
}
