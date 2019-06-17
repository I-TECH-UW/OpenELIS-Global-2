package us.mn.state.health.lims.common.services;

import java.sql.Timestamp;
import java.util.List;

import us.mn.state.health.lims.common.services.ReportTrackingService.ReportType;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface IReportTrackingService {

	public void addReports(List<String> refIds, ReportType type, String name, String currentSystemUserId);

	public List<DocumentTrack> getReportsForSample(Sample sample, ReportType type);

	public List<DocumentTrack> getReportsForSampleAndReportName(Sample sample, ReportType type, String name);

	public DocumentTrack getLastReportForSample(Sample sample, ReportType type);

	public DocumentTrack getLastNamedReportForSample(Sample sample, ReportType type, String name);

	public Timestamp getTimeOfLastReport(Sample sample, ReportType type);

	public Timestamp getTimeOfLastNamedReport(Sample sample, ReportType type, String name);

	public DocumentTrack getDocumentForId(String id);
}
