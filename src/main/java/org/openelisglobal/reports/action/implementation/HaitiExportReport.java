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
package org.openelisglobal.reports.action.implementation;

import org.apache.commons.beanutils.PropertyUtils;
import org.jfree.util.Log;
import org.openelisglobal.common.form.BaseForm;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.reports.action.implementation.reportBeans.HaitiColumnBuilder;
import org.openelisglobal.reports.action.implementation.reportBeans.ResourceTranslator;

/**
 * @author pahill (pahill@uw.edu)
 * @since Mar 17, 2011
 */
public class HaitiExportReport extends CSVSampleExportReport implements IReportParameterSetter {

    @Override
    protected String reportFileName() {
        return "HaitiExportReport";
    }

    @Override
    public void setRequestParameters(BaseForm form) {
        try {
            PropertyUtils.setProperty(form, "reportName", getReportNameForParameterPage());
            PropertyUtils.setProperty(form, "useLowerDateRange", Boolean.TRUE);
            PropertyUtils.setProperty(form, "useUpperDateRange", Boolean.TRUE);
        } catch (Exception e) {
            Log.error("Error in " + this.getClass().getSimpleName() + ".setRequestParemeters: ", e);
        }
    }

    protected String getReportNameForParameterPage() {
        return MessageUtil.getMessage("reports.label.project.export") + " "
                + MessageUtil.getContextualMessage("sample.collectionDate");
    }

    /**
     * @see org.openelisglobal.reports.action.implementation.IReportCreator#initializeReport(org.openelisglobal.common.action.BaseActionForm)
     */
    @Override
    public void initializeReport(BaseForm form) {
        super.initializeReport();
        errorFound = false;

        lowDateStr = form.getString("lowerDateRange");
        highDateStr = form.getString("upperDateRange");
        dateRange = new DateRange(lowDateStr, highDateStr);

        createReportParameters();

        errorFound = !validateSubmitParameters();
        if (errorFound) {
            return;
        }

        createReportItems();
    }

    /**
     * check everything
     */
    private boolean validateSubmitParameters() {
        return dateRange.validateHighLowDate("report.error.message.date.received.missing");
    }

    /**
     * creating the list for generation to the report, putting results in resultSet
     */
    private void createReportItems() {
        try {
            // we have to round up everything via hibernate first, since many of our methods
            // close the connection
            ResourceTranslator.DictionaryTranslator.getInstance();
            ResourceTranslator.GenderTranslator.getInstance();

            csvColumnBuilder = new HaitiColumnBuilder(dateRange);
            csvColumnBuilder.buildDataSource();
        } catch (Exception e) {
            Log.error("Error in " + this.getClass().getSimpleName() + ".createReportItems: ", e);
            LogEvent.logDebug(e);
            add1LineErrorMessage("report.error.message.general.error");
        }
    }
}
