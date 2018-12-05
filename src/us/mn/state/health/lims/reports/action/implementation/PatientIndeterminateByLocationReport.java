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


import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import net.sf.jasperreports.engine.JRDataSource;
import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.organization.util.OrganizationTypeList;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.IndeterminateReportData;
import us.mn.state.health.lims.sampleproject.dao.SampleProjectDAO;
import us.mn.state.health.lims.sampleproject.daoimpl.SampleProjectDAOImpl;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;


public class PatientIndeterminateByLocationReport extends PatientIndeterminateReport implements IReportParameterSetter, IReportCreator {

    private String lowDateStr;
    private String highDateStr;
    private String locationStr;
    private DateRange dateRange;

    @Override
    protected String reportFileName(){
        return "Patient_Indeterminate_ByLocation";
    }

	public void setRequestParameters(BaseActionForm dynaForm) {
        try {
            PropertyUtils.setProperty(dynaForm, "reportName", StringUtil.getMessageForKey("reports.label.patient.indeterminate"));

            PropertyUtils.setProperty(dynaForm, "useLowerDateRange", Boolean.TRUE);
            PropertyUtils.setProperty(dynaForm, "useUpperDateRange", Boolean.TRUE);

            PropertyUtils.setProperty(dynaForm, "useLocationCode", Boolean.TRUE);
            List<Organization> list = OrganizationTypeList.EID_ORGS_BY_NAME.getList();
            PropertyUtils.setProperty(dynaForm, "locationCodeList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public void initializeReport(BaseActionForm dynaForm) {
        super.initializeReport();
        errorFound = false;

        lowDateStr = dynaForm.getString("lowerDateRange");
        highDateStr = dynaForm.getString("upperDateRange");
        locationStr = dynaForm.getString("locationCode");
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
        return (dateRange.validateHighLowDate("report.error.message.date.received.missing") && getValidOrganization(locationStr) != null );
    }

    /**
     * @see us.mn.state.health.lims.reports.action.implementation.PatientIndeterminateReport#createReportItems()
     */
    @Override
    protected void createReportItems() {
        SampleProjectDAO dao = new SampleProjectDAOImpl();
        List<SampleProject> sampleProject = dao.getByOrganizationProjectAndReceivedOnRange(locationStr, "Indeterminate Results", dateRange.getLowDate(), dateRange.getHighDate());

        if (sampleProject.isEmpty()) {
            add1LineErrorMessage("report.error.message.noPrintableItems");
            return;
        }

        for (SampleProject so : sampleProject) {
            // slightly kludgy, but we take one of the samples and set it in this reporting object so existing methods can operate on it, on the patient etc.
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

    public HashMap<String, ?> getReportParameters() throws IllegalStateException {
        return super.getReportParameters();
    }
    public JRDataSource getReportDataSource() throws IllegalStateException {
        return super.getReportDataSource();
    }
    

	@Override
	protected void createReportParameters() {
	    super.createReportParameters();
		reportParameters.put("contact", "CHU de Treichville, 01 BP 1712 Tel : 21-21-42-50/21-25-4189 Fax : 21-24-29-69/ 21-25-10-63");
	}
}
