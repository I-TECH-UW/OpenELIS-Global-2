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
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;

public interface ReportExternalExportDAO extends BaseDAO{
	public List<ReportExternalExport> getRecalculateReportExports(String reportQueueTypeId) throws LIMSRuntimeException;
	public List<ReportExternalExport> getUnsentReportExports(String reportQueueTypeId) throws LIMSRuntimeException;
	public ReportExternalExport getLatestSentReportExport(String reportQueueTypeId) throws LIMSRuntimeException;
	public ReportExternalExport getLatestEventReportExport(String reportQueueTypeId) throws LIMSRuntimeException;
	public List<ReportExternalExport> getReportsInDateRange(Timestamp lower, Timestamp upper, String reportQueueTypeId) throws LIMSRuntimeException;
	public void insertReportExternalExport( ReportExternalExport report) throws LIMSRuntimeException;
	public void updateReportExternalExport( ReportExternalExport report) throws LIMSRuntimeException;
	public Timestamp getLastSentTimestamp() throws LIMSRuntimeException;
	public Timestamp getLastCollectedTimestamp() throws LIMSRuntimeException;
	public ReportExternalExport getReportByEventDateAndType(ReportExternalExport report) throws LIMSRuntimeException;
	public ReportExternalExport loadReport(ReportExternalExport report) throws LIMSRuntimeException;
	public ReportExternalExport readReportExternalExport(String idString) throws LIMSRuntimeException;
	public void delete(ReportExternalExport report) throws LIMSRuntimeException;
}
