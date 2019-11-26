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

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.organization.util.OrganizationTypeList;
import org.openelisglobal.organization.valueholder.Organization;
import org.openelisglobal.reports.action.implementation.reportBeans.IndeterminateReportData;
import org.openelisglobal.sampleproject.service.SampleProjectService;
import org.openelisglobal.sampleproject.valueholder.SampleProject;
import org.openelisglobal.spring.util.SpringContext;

import net.sf.jasperreports.engine.JRDataSource;

public class PatientIndeterminateByLocationReport extends PatientIndeterminateReport
        implements IReportParameterSetter, IReportCreator {

    private String lowDateStr;
    private String highDateStr;
    private String locationStr;
    private DateRange dateRange;

    private SampleProjectService sampleProjectService = SpringContext.getBean(SampleProjectService.class);

    @Override
    protected String reportFileName() {
        return "Patient_Indeterminate_ByLocation";
    }

    @Override
    public void setRequestParameters(BaseForm form) {
        try {
            PropertyUtils.setProperty(form, "reportName",
                    MessageUtil.getMessage("reports.label.patient.indeterminate"));

            PropertyUtils.setProperty(form, "useLowerDateRange", Boolean.TRUE);
            PropertyUtils.setProperty(form, "useUpperDateRange", Boolean.TRUE);

            PropertyUtils.setProperty(form, "useLocationCode", Boolean.TRUE);
            List<Organization> list = OrganizationTypeList.EID_ORGS_BY_NAME.getList();
            PropertyUtils.setProperty(form, "locationCodeList", list);
        } catch (Exception e) {
            LogEvent.logDebug(e);
        }
    }

    @Override
    public void initializeReport(BaseForm form) {
        super.initializeReport();
        errorFound = false;

        lowDateStr = form.getString("lowerDateRange");
        highDateStr = form.getString("upperDateRange");
        locationStr = form.getString("locationCode");
        dateRange = new DateRange(lowDateStr, highDateStr);

        createReportParameters();

        errorFound = !validateSubmitParameters();
        if (errorFound) {
            return;
        }

        initializeReportItems();
        createReportItems();
    }

    /**
     * check everything
     */
    private boolean validateSubmitParameters() {
        return (dateRange.validateHighLowDate("report.error.message.date.received.missing")
                && getValidOrganization(locationStr) != null);
    }

    /**
     * @see org.openelisglobal.reports.action.implementation.PatientIndeterminateReport#createReportItems()
     */
    @Override
    protected void createReportItems() {
        List<SampleProject> sampleProject = sampleProjectService.getByOrganizationProjectAndReceivedOnRange(locationStr,
                "Indeterminate Results", dateRange.getLowDate(), dateRange.getHighDate());

        if (sampleProject.isEmpty()) {
            add1LineErrorMessage("report.error.message.noPrintableItems");
            return;
        }

        for (SampleProject so : sampleProject) {
            // slightly kludgy, but we take one of the samples and set it in this reporting
            // object so existing methods can operate on it, on the patient etc.
            reportSample = so.getSample();
            IndeterminateReportData reportData = new IndeterminateReportData();
            findPatientFromSample();
            setTestInfo(reportData);
            setPatientInfo(reportData);
            reportData.setStatus("");
            reportItems.add(reportData);
        }
        reportSample = null;
        reportPatient = null;
    }

    @Override
    public HashMap<String, Object> getReportParameters() throws IllegalStateException {
        return super.getReportParameters();
    }

    @Override
    public JRDataSource getReportDataSource() throws IllegalStateException {
        return super.getReportDataSource();
    }

    @Override
    protected void createReportParameters() {
        super.createReportParameters();
        reportParameters.put("contact",
                "CHU de Treichville, 01 BP 1712 Tel : 21-21-42-50/21-25-4189 Fax : 21-24-29-69/ 21-25-10-63");
    }
}
