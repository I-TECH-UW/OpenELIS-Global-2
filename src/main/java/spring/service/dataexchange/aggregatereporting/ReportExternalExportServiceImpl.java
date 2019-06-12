package spring.service.dataexchange.aggregatereporting;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.dao.ReportExternalExportDAO;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;

@Service
public class ReportExternalExportServiceImpl extends BaseObjectServiceImpl<ReportExternalExport, String>
		implements ReportExternalExportService {
	@Autowired
	protected ReportExternalExportDAO baseObjectDAO;

	ReportExternalExportServiceImpl() {
		super(ReportExternalExport.class);
		disableLogging();
	}

	@Override
	protected ReportExternalExportDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public Timestamp getLastCollectedTimestamp() {
		return getBaseObjectDAO().getLastCollectedTimestamp();
	}

	@Override
	public ReportExternalExport getReportByEventDateAndType(ReportExternalExport report) {
		return getBaseObjectDAO().getReportByEventDateAndType(report);
	}

	@Override
	public List<ReportExternalExport> getReportsInDateRange(Timestamp lower, Timestamp upper,
			String reportQueueTypeId) {
		return getBaseObjectDAO().getReportsInDateRange(lower, upper, reportQueueTypeId);
	}

	@Override
	public ReportExternalExport getLatestSentReportExport(String reportQueueTypeId) {
		return getBaseObjectDAO().getLatestSentReportExport(reportQueueTypeId);
	}

	@Override
	public ReportExternalExport readReportExternalExport(String idString) {
		return getBaseObjectDAO().readReportExternalExport(idString);
	}

	@Override
	public ReportExternalExport getLatestEventReportExport(String reportQueueTypeId) {
		return getBaseObjectDAO().getLatestEventReportExport(reportQueueTypeId);
	}

	@Override
	public Timestamp getLastSentTimestamp() {
		return getBaseObjectDAO().getLastSentTimestamp();
	}

	@Override
	public List<ReportExternalExport> getUnsentReportExports(String reportQueueTypeId) {
		return getBaseObjectDAO().getUnsentReportExports(reportQueueTypeId);
	}

	@Override
	public List<ReportExternalExport> getRecalculateReportExports(String reportQueueTypeId) {
		return getBaseObjectDAO().getRecalculateReportExports(reportQueueTypeId);
	}

	@Override
	public ReportExternalExport loadReport(ReportExternalExport report) {
		return getBaseObjectDAO().loadReport(report);
	}
}
