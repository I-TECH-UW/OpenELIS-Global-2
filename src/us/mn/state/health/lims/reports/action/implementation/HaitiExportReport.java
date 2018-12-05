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
package us.mn.state.health.lims.reports.action.implementation;

import org.apache.commons.beanutils.PropertyUtils;
import org.jfree.util.Log;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.HaitiColumnBuilder;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.ResourceTranslator;

/**
 * @author pahill (pahill@uw.edu)
 * @since Mar 17, 2011
 */
public class HaitiExportReport extends CSVSampleExportReport implements IReportParameterSetter {
	
	@Override
	protected String reportFileName(){
		return "HaitiExportReport";
	}

    public void setRequestParameters(BaseActionForm dynaForm) {
        try {
            PropertyUtils.setProperty(dynaForm, "reportName", getReportNameForParameterPage());
            PropertyUtils.setProperty(dynaForm, "useLowerDateRange", Boolean.TRUE);
            PropertyUtils.setProperty(dynaForm, "useUpperDateRange", Boolean.TRUE);
        } catch ( Exception e ) {
            Log.error("Error in " + this.getClass().getSimpleName() + ".setRequestParemeters: ", e);
        }
    }

    protected String getReportNameForParameterPage() {
        return StringUtil.getMessageForKey("reports.label.project.export") + " " + StringUtil.getContextualMessageForKey("sample.collectionDate");
    }

    /**
     * @see us.mn.state.health.lims.reports.action.implementation.IReportCreator#initializeReport(us.mn.state.health.lims.common.action.BaseActionForm)
     */
    @Override
    public void initializeReport(BaseActionForm dynaForm) {
    	super.initializeReport();
        errorFound = false;

        lowDateStr = dynaForm.getString("lowerDateRange");
        highDateStr = dynaForm.getString("upperDateRange");
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
            // we have to round up everything via hibernate first, since many of our DAO methods close the connection
            ResourceTranslator.DictionaryTranslator.getInstance();
            ResourceTranslator.GenderTranslator.getInstance();

            csvColumnBuilder = new HaitiColumnBuilder(dateRange);
            csvColumnBuilder.buildDataSource();
        } catch ( Exception e ) {
            Log.error("Error in " + this.getClass().getSimpleName() + ".createReportItems: ", e);
            e.printStackTrace();
            add1LineErrorMessage("report.error.message.general.error");
        }
    }
}
