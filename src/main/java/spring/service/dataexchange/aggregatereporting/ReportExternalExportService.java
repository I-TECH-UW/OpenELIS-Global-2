package spring.service.dataexchange.aggregatereporting;

import java.sql.Timestamp;
import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;

public interface ReportExternalExportService extends BaseObjectService<ReportExternalExport, String> {

	@Override
	public void delete(ReportExternalExport report);

	Timestamp getLastCollectedTimestamp();

	ReportExternalExport getReportByEventDateAndType(ReportExternalExport report);

	List<ReportExternalExport> getReportsInDateRange(Timestamp lower, Timestamp upper, String reportQueueTypeId);

	ReportExternalExport getLatestSentReportExport(String reportQueueTypeId);

	void insertReportExternalExport(ReportExternalExport report);

	ReportExternalExport readReportExternalExport(String idString);

	ReportExternalExport getLatestEventReportExport(String reportQueueTypeId);

	Timestamp getLastSentTimestamp();

	void updateReportExternalExport(ReportExternalExport report);

	List<ReportExternalExport> getUnsentReportExports(String reportQueueTypeId);

	List<ReportExternalExport> getRecalculateReportExports(String reportQueueTypeId);

	ReportExternalExport loadReport(ReportExternalExport report);
}
