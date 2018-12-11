package us.mn.state.health.lims.reports.action.implementation;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.ReportTrackingService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.common.services.TestService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.reports.action.implementation.reportBeans.VLReportData;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sampleorganization.dao.SampleOrganizationDAO;
import us.mn.state.health.lims.sampleorganization.daoimpl.SampleOrganizationDAOImpl;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;

public abstract class PatientVLReport extends RetroCIPatientReport {


    protected static final long YEAR = 1000L * 60L * 60L * 24L * 365L;
	protected static final long THREE_YEARS = YEAR * 3L;
	protected static final long WEEK = YEAR / 52L;
	protected static final long MONTH = YEAR / 12L;

	protected List<VLReportData> reportItems;
	private String invalidValue = StringUtil.getMessageForKey("report.test.status.inProgress");

	protected void initializeReportItems() {
		reportItems = new ArrayList<VLReportData>();
	}

	protected String getReportNameForReport() {
		return StringUtil.getMessageForKey("reports.label.patient.VL");
	}

	public JRDataSource getReportDataSource() throws IllegalStateException {
		if (!initialized) {
			throw new IllegalStateException("initializeReport not called first");
		}

		return errorFound ? new JRBeanCollectionDataSource(errorMsgs) : new JRBeanCollectionDataSource(reportItems);
	}

	protected void createReportItems() {
		VLReportData data = new VLReportData();

		setPatientInfo(data);
		setTestInfo(data);
		reportItems.add(data);

	}

	protected void setTestInfo(VLReportData data) {
		boolean atLeastOneAnalysisNotValidated = false;
		AnalysisDAO analysisDAO = new AnalysisDAOImpl();
		List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(reportSample.getId());
//		DictionaryDAO dictionaryDAO = new DictionaryDAOImpl();
		Timestamp lastReport = new ReportTrackingService().getTimeOfLastNamedReport(reportSample, ReportTrackingService.ReportType.PATIENT, requestedReport);
		Boolean mayBeDuplicate = lastReport != null;
		ResultDAO resultDAO = new ResultDAOImpl();

		Date maxCompleationDate = null;
		long maxCompleationTime = 0L;
//		String invalidValue = StringUtil.getMessageForKey("report.test.status.inProgress");

		for (Analysis analysis : analysisList) {

			if (analysis.getCompletedDate() != null) {
				if (analysis.getCompletedDate().getTime() > maxCompleationTime) {
					maxCompleationDate = analysis.getCompletedDate();
					maxCompleationTime = maxCompleationDate.getTime();
				}

			}

			String testName = TestService.getUserLocalizedTestName( analysis.getTest() );

			List<Result> resultList = resultDAO.getResultsByAnalysis(analysis);
			

			boolean valid = ANALYSIS_FINALIZED_STATUS_ID.equals(analysis.getStatusId());
			if (!valid) {
				atLeastOneAnalysisNotValidated = true;
			}
			
			

			if (testName.equals("Viral Load")) {
				if (valid) {
				//	data.setShowVirologie(Boolean.TRUE);
					String resultValue = "";
					if( resultList.size() > 0){
						resultValue = resultList.get( resultList.size() - 1).getValue();
					}
					
					String baseValue = resultValue;
					if(!GenericValidator.isBlankOrNull(resultValue) && resultValue.contains("(")){
						String[] splitValue = resultValue.split("\\(");
						data.setAmpli2(splitValue[0]);
						baseValue = splitValue[0];
					}else{
						data.setAmpli2(resultValue);
					}
					if(!GenericValidator.isBlankOrNull(baseValue) && !"0".equals(baseValue)){
						try{
							double viralLoad = Double.parseDouble(baseValue);
							data.setAmpli2lo(String.format("%.3g%n", Math.log10(viralLoad)));
						}catch(NumberFormatException nfe){
							data.setAmpli2lo("");
						}
					}
					
				}
				
			}
			if( mayBeDuplicate &&
					StatusService.getInstance().matches( analysis.getStatusId(), AnalysisStatus.Finalized) &&
					lastReport.before(analysis.getLastupdated())){
				mayBeDuplicate = false;
			}

		}
		if (maxCompleationDate != null) {
			data.setCompleationdate(DateUtil.convertSqlDateToStringDate(maxCompleationDate));
		}

		data.setDuplicateReport(mayBeDuplicate);
		data.setStatus(atLeastOneAnalysisNotValidated ? StringUtil.getMessageForKey("report.status.partial") : StringUtil
				.getMessageForKey("report.status.complete"));
	}

	protected void setPatientInfo(VLReportData data) {

		SampleOrganizationDAO orgDAO = new SampleOrganizationDAOImpl();

		data.setSubjectno(reportPatient.getNationalId());
		data.setSitesubjectno(reportPatient.getExternalId());
		data.setBirth_date(reportPatient.getBirthDateForDisplay());
		data.setAge(DateUtil.getCurrentAgeForDate(reportPatient.getBirthDate(), reportSample.getCollectionDate()));
		data.setGender(reportPatient.getGender());
		data.setCollectiondate( DateUtil.convertTimestampToStringDateAndTime(reportSample.getCollectionDate()));
		SampleOrganization sampleOrg = new SampleOrganization();
		sampleOrg.setSample(reportSample);
		orgDAO.getDataBySample(sampleOrg);
		data.setServicename(sampleOrg.getId() == null ? "" : sampleOrg.getOrganization().getOrganizationName());
		data.setDoctor(getObservationValues(OBSERVATION_DOCTOR_ID));
		data.setAccession_number(reportSample.getAccessionNumber());
		data.setReceptiondate( DateUtil.convertTimestampToStringDateAndTime(reportSample.getReceivedTimestamp()));           
		Timestamp collectionDate = reportSample.getCollectionDate();

		if (collectionDate != null) {
			long collectionTime = collectionDate.getTime() - reportPatient.getBirthDate().getTime();

			if (collectionTime < THREE_YEARS) {
				data.setAgeWeek(String.valueOf((int) Math.floor(collectionTime / WEEK)));
			} else {
				data.setAgeMonth(String.valueOf((int) Math.floor(collectionTime / MONTH)));
			}

		}
		data.getSampleQaEventItems(reportSample);
	}

	protected String getProjectId() {
		return ANTIRETROVIRAL_STUDY_ID+":"+ANTIRETROVIRAL_FOLLOW_UP_STUDY_ID+":"+VL_STUDY_ID;
		//return ANTIRETROVIRAL_ID;
	}

}
