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
package us.mn.state.health.lims.dataexchange.aggregatereporting.dao;

import java.sql.Timestamp;
import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalImport;

public interface ReportExternalImportDAO extends BaseDAO{
	public List<ReportExternalImport> getReportsInDateRangeSorted(Timestamp lower, Timestamp upper) throws LIMSRuntimeException;
	public void insertReportExternalImport( ReportExternalImport report) throws LIMSRuntimeException;
	public void updateReportExternalImport( ReportExternalImport report) throws LIMSRuntimeException;
	public List<String> getUniqueSites() throws LIMSRuntimeException;
	public List<ReportExternalImport> getReportsInDateRangeSortedForSite(Timestamp beginning, Timestamp end, String site) throws LIMSRuntimeException;
	public ReportExternalImport getReportByEventDateSiteType(ReportExternalImport importReport) throws LIMSRuntimeException;
}
