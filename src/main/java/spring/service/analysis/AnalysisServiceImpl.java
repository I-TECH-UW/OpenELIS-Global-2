package spring.service.analysis;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.service.test.TestServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import spring.service.note.NoteServiceImpl;
import us.mn.state.health.lims.common.services.QAService;
import us.mn.state.health.lims.common.services.ReportTrackingService;
import spring.service.result.ResultServiceImpl;
import us.mn.state.health.lims.common.services.StatusService;
import spring.service.typeofsample.TypeOfSampleServiceImpl;
import spring.service.typeoftestresult.TypeOfTestResultServiceImpl;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.dictionary.dao.DictionaryDAO;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Service
@DependsOn({ "springContext" })
public class AnalysisServiceImpl extends BaseObjectServiceImpl<Analysis> implements AnalysisService {

	@Autowired
	protected AnalysisDAO analysisDAO;

	@Autowired
	private DictionaryDAO dictionaryDAO = SpringContext.getBean(DictionaryDAO.class);
	@Autowired
	private ResultDAO resultDAO = SpringContext.getBean(ResultDAO.class);
	@Autowired
	private TypeOfSampleDAO typeOfSampleDAO = SpringContext.getBean(TypeOfSampleDAO.class);
	@Autowired
	private ReferenceTablesDAO referenceTablesDAO = SpringContext.getBean(ReferenceTablesDAO.class);

	private Analysis analysis;
	public static String TABLE_REFERENCE_ID;
	private final String DEFAULT_ANALYSIS_TYPE = "MANUAL";

	public synchronized void initializeGlobalVariables() {
		if (TABLE_REFERENCE_ID == null) {
			TABLE_REFERENCE_ID = referenceTablesDAO.getReferenceTableByName("ANALYSIS").getId();
		}
	}

	public AnalysisServiceImpl() {
		super(Analysis.class);
		initializeGlobalVariables();

	}

	public AnalysisServiceImpl(Analysis analysis) {
		this();
		this.analysis = analysis;
	}

	public AnalysisServiceImpl(String analysisId) {
		this();
		if (!GenericValidator.isBlankOrNull(analysisId)) {
			analysis = analysisDAO.getAnalysisById(analysisId);
		}
	}

	@Override
	public Analysis getAnalysis() {
		return analysis;
	}

	@Override
	public String getTestDisplayName() {
		if (analysis == null) {
			return "";
		}
		Test test = getTest();
		String name = TestServiceImpl.getLocalizedTestNameWithType(test);

		TypeOfSample typeOfSample = TypeOfSampleServiceImpl.getTypeOfSampleForTest(test.getId());

		if (typeOfSample != null
				&& typeOfSample.getId().equals(TypeOfSampleServiceImpl.getTypeOfSampleIdForLocalAbbreviation("Variable"))) {
			name += "(" + analysis.getSampleTypeName() + ")";
		}

		String parentResultType = analysis.getParentResult() != null ? analysis.getParentResult().getResultType() : "";
		if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(parentResultType)) {
			Dictionary dictionary = dictionaryDAO.getDictionaryById(analysis.getParentResult().getValue());
			if (dictionary != null) {
				String parentResult = dictionary.getLocalAbbreviation();
				if (GenericValidator.isBlankOrNull(parentResult)) {
					parentResult = dictionary.getDictEntry();
				}
				name = parentResult + " &rarr; " + name;
			}
		}

		return name;
	}

	@Override
	public String getCSVMultiselectResults() {
		if (analysis == null) {
			return "";
		}
		List<Result> existingResults = resultDAO.getResultsByAnalysis(analysis);
		StringBuilder multiSelectBuffer = new StringBuilder();
		for (Result existingResult : existingResults) {
			if (TypeOfTestResultServiceImpl.ResultType.isMultiSelectVariant(existingResult.getResultType())) {
				multiSelectBuffer.append(existingResult.getValue());
				multiSelectBuffer.append(',');
			}
		}

		// remove last ','
		multiSelectBuffer.setLength(multiSelectBuffer.length() - 1);

		return multiSelectBuffer.toString();
	}

	@Override
	public String getJSONMultiSelectResults() {
		return analysis == null ? ""
				: ResultServiceImpl.getJSONStringForMultiSelect(resultDAO.getResultsByAnalysis(analysis));
	}

	@Override
	public Result getQuantifiedResult() {
		if (analysis == null) {
			return null;
		}
		List<Result> existingResults = resultDAO.getResultsByAnalysis(analysis);
		List<String> quantifiableResultsIds = new ArrayList<>();
		for (Result existingResult : existingResults) {
			if (TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(existingResult.getResultType())) {
				quantifiableResultsIds.add(existingResult.getId());
			}
		}

		for (Result existingResult : existingResults) {
			if (!TypeOfTestResultServiceImpl.ResultType.isDictionaryVariant(existingResult.getResultType())
					&& existingResult.getParentResult() != null
					&& quantifiableResultsIds.contains(existingResult.getParentResult().getId())
					&& !GenericValidator.isBlankOrNull(existingResult.getValue())) {
				return existingResult;
			}
		}

		return null;
	}

	@Override
	public String getCompletedDateForDisplay() {
		return analysis == null ? "" : analysis.getCompletedDateForDisplay();
	}

	@Override
	public String getAnalysisType() {
		return analysis == null ? "" : analysis.getAnalysisType();
	}

	@Override
	public String getStatusId() {
		return analysis == null ? "" : analysis.getStatusId();
	}

	@Override
	public Boolean getTriggeredReflex() {
		return analysis == null ? false : analysis.getTriggeredReflex();
	}

	@Override
	public boolean resultIsConclusion(Result currentResult) {
		if (analysis == null || currentResult == null) {
			return false;
		}
		List<Result> results = resultDAO.getResultsByAnalysis(analysis);
		if (results.size() == 1) {
			return false;
		}

		Long testResultId = Long.parseLong(currentResult.getId());
		// This based on the fact that the conclusion is always added
		// after the shared result so if there is a result with a larger id
		// then this is not a conclusion
		for (Result result : results) {
			if (Long.parseLong(result.getId()) > testResultId) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isParentNonConforming() {
		return analysis == null ? false : QAService.isAnalysisParentNonConforming(analysis);
	}

	@Override
	public Test getTest() {
		return analysis == null ? null : analysis.getTest();
	}

	@Override
	public List<Analysis> getAnalysisStartedOrCompletedInDateRange(Date lowDate, Date highDate) {
		return analysisDAO.getAnalysisStartedOrCompletedInDateRange(lowDate, highDate);
	}

	@Override
	public List<Result> getResults() {
		return analysis == null ? new ArrayList<>() : resultDAO.getResultsByAnalysis(analysis);
	}

	@Override
	public boolean hasBeenCorrectedSinceLastPatientReport() {
		return analysis == null ? false : analysis.isCorrectedSincePatientReport();
	}

	@Override
	public boolean patientReportHasBeenDone() {
		return analysis == null ? false
				: new ReportTrackingService().getLastReportForSample(analysis.getSampleItem().getSample(),
						ReportTrackingService.ReportType.PATIENT) != null;
	}

	@Override
	public String getNotesAsString(boolean prefixType, boolean prefixTimestamp, String noteSeparator,
			boolean excludeExternPrefix) {
		return analysis == null ? ""
				: new NoteServiceImpl(analysis).getNotesAsString(prefixType, prefixTimestamp, noteSeparator,
						excludeExternPrefix);
	}

	@Override
	public String getOrderAccessionNumber() {
		return analysis == null ? "" : analysis.getSampleItem().getSample().getAccessionNumber();
	}

	@Override
	public TypeOfSample getTypeOfSample() {
		return analysis == null ? null
				: typeOfSampleDAO.getTypeOfSampleById(analysis.getSampleItem().getTypeOfSampleId());
	}

	@Override
	public Panel getPanel() {
		return analysis == null ? null : analysis.getPanel();
	}

	@Override
	public TestSection getTestSection() {
		return analysis == null ? null : analysis.getTestSection();
	}

	@Override
	public Analysis buildAnalysis(Test test, SampleItem sampleItem) {

		Analysis analysis = new Analysis();
		analysis.setTest(test);
		analysis.setIsReportable(test.getIsReportable());
		analysis.setAnalysisType(DEFAULT_ANALYSIS_TYPE);
		analysis.setRevision("0");
		analysis.setStartedDate(DateUtil.getNowAsSqlDate());
		analysis.setStatusId(StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.NotStarted));
		analysis.setSampleItem(sampleItem);
		analysis.setTestSection(test.getTestSection());
		analysis.setSampleTypeName(sampleItem.getTypeOfSample().getLocalizedName());

		return analysis;
	}

	@Override
	protected AnalysisDAO getBaseObjectDAO() {
		return analysisDAO;
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleId(String id) {
		return analysisDAO.getAnalysesBySampleId(id);
	}

	@Override
	@Transactional
	public void insert(Analysis analysis, boolean duplicateCheck) {
		analysisDAO.insertData(analysis, duplicateCheck);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysisByAccessionAndTestId(String accessionNumber, String testId) {
		return analysisDAO.getAnalysisByAccessionAndTestId(accessionNumber, testId);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysisCollectedOnExcludedByStatusId(Date date, Set<Integer> excludedStatusIds) {
		return analysisDAO.getAnalysisCollectedOnExcludedByStatusId(date, excludedStatusIds);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleItemsExcludingByStatusIds(SampleItem sampleItem,
			Set<Integer> excludedStatusIds) {
		return analysisDAO.getAnalysesBySampleItemsExcludingByStatusIds(sampleItem, excludedStatusIds);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesForStatusId(String status) {
		return analysisDAO.getAllMatching("statusId", status);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleStatusIdExcludingByStatusId(String sampleStatus,
			Set<Integer> excludedStatusIds) {
		return analysisDAO.getAnalysesBySampleStatusIdExcludingByStatusId(sampleStatus, excludedStatusIds);
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestAndExcludedStatus(String testId, List<Integer> excludedStatusIntList) {
		return analysisDAO.getAllAnalysisByTestAndExcludedStatus(testId, excludedStatusIntList);
	}

	@Override
	@Transactional
	public void updateAnalysises(List<Analysis> cancelAnalysis, List<Analysis> newAnalysis, String sysUserId) {
		String cancelStatus = StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.Canceled);
		for (Analysis analysis : cancelAnalysis) {
			analysis.setStatusId(cancelStatus);
			analysis.setSysUserId(sysUserId);
			update(analysis);
		}

		for (Analysis analysis : newAnalysis) {
			analysis.setSysUserId(sysUserId);
			insert(analysis, false);
		}
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestAndStatus(String id, List<Integer> statusList) {
		return analysisDAO.getAllAnalysisByTestAndStatus(id, statusList);
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestsAndStatus(List<String> nfsTestIdList, List<Integer> statusList) {
		return analysisDAO.getAllAnalysisByTestsAndStatus(nfsTestIdList, statusList);
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestSectionAndStatus(String sectionId, List<Integer> statusList,
			boolean sortedByDateAndAccession) {
		return analysisDAO.getAllAnalysisByTestSectionAndStatus(sectionId, statusList, sortedByDateAndAccession);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleItemIdAndStatusId(String sampleItemId, String canceledTestStatusId) {
		return analysisDAO.getAnalysesBySampleItemIdAndStatusId(sampleItemId, canceledTestStatusId);
	}
}
