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
package org.openelisglobal.reports.action.implementation.reportBeans;

import java.sql.Date;
import org.openelisglobal.reports.action.implementation.Report.DateRange;
import org.openelisglobal.reports.service.WHONetReportService;
import org.openelisglobal.spring.util.SpringContext;

/**
 * @author pahill (pahill@uw.edu)
 * @since May 18, 2011
 */
public class WHONETRoutineColumnBuilder extends WHONETCIRoutineColumnBuilder {

    /**
     * @param dateRange
     * @param projectStr
     */
    public WHONETRoutineColumnBuilder(DateRange dateRange) {
        super(dateRange);
    }

    @Override
    public void searchForWHONetResults() {
        WHONetReportService reportService = SpringContext.getBean(WHONetReportService.class);
        Date lowDate = dateRange.getLowDate();
        Date highDate = dateRange.getHighDate();
        rows = reportService.getWHONetRows(lowDate, highDate);
        return;
    }

}
