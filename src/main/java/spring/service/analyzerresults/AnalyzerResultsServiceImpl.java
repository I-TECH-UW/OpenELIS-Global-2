package spring.service.analyzerresults;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.mine.result.controller.AnalyzerResultsController.SampleGrouping;
import spring.service.analysis.AnalysisService;
import spring.service.common.BaseObjectServiceImpl;
import spring.service.note.NoteService;
import spring.service.result.ResultService;
import spring.service.sample.SampleService;
import spring.service.samplehuman.SampleHumanService;
import spring.service.sampleitem.SampleItemService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyzerresults.dao.AnalyzerResultsDAO;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.RecordStatus;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.result.action.util.ResultUtil;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testreflex.action.util.TestReflexBean;
import us.mn.state.health.lims.testreflex.action.util.TestReflexUtil;

@Service
public class AnalyzerResultsServiceImpl extends BaseObjectServiceImpl<AnalyzerResults, String>
		implements AnalyzerResultsService {
	@Autowired
	protected AnalyzerResultsDAO baseObjectDAO;

	@Autowired
	private NoteService noteService;
	@Autowired
	private SampleHumanService sampleHumanService;
	@Autowired
	private SampleItemService sampleItemService;
	@Autowired
	private SampleService sampleService;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private ResultService resultService;

	AnalyzerResultsServiceImpl() {
		super(AnalyzerResults.class);
	}

	@Override
	protected AnalyzerResultsDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<AnalyzerResults> getResultsbyAnalyzer(String analyzerId) {
		return baseObjectDAO.getAllMatchingOrdered("analyzerId", analyzerId, "id", false);
	}

	@Override
	public AnalyzerResults readAnalyzerResults(String idString) {
		return getBaseObjectDAO().readAnalyzerResults(idString);
	}

	@Override
	public void insertAnalyzerResults(List<AnalyzerResults> results, String sysUserId) {
		try {
			for (AnalyzerResults result : results) {
				boolean duplicateByAccessionAndTestOnly = false;
				List<AnalyzerResults> previousResults = baseObjectDAO.getDuplicateResultByAccessionAndTest(result);
				AnalyzerResults previousResult = null;

				// This next block may seem more complicated then it need be but it covers the
				// case where there may be a third duplicate
				// and it covers rereading the same file
				if (previousResults != null) {
					duplicateByAccessionAndTestOnly = true;
					for (AnalyzerResults foundResult : previousResults) {
						previousResult = foundResult;
						if (foundResult.getCompleteDate().equals(result.getCompleteDate())) {
							duplicateByAccessionAndTestOnly = false;
							break;
						}
					}
				}

				if (duplicateByAccessionAndTestOnly) {
					result.setDuplicateAnalyzerResultId(previousResult.getId());
					result.setReadOnly(true);
				}

				if (previousResults == null || duplicateByAccessionAndTestOnly) {

					String id = insert(result);
					result.setId(id);

					if (duplicateByAccessionAndTestOnly) {
						previousResult.setDuplicateAnalyzerResultId(id);
						previousResult.setSysUserId(sysUserId);
					}

					if (duplicateByAccessionAndTestOnly) {
						update(previousResult);
					}
				}
			}

		} catch (Exception e) {
			LogEvent.logError("AnalyzerResultDAOImpl", "insertAnalyzerResult()", e.toString());
			throw new LIMSRuntimeException("Error in AnalyzerResult insertAnalyzerResult()", e);
		}
	}

	@Override
	@Transactional
	public void persistAnalyzerResults(List<AnalyzerResults> deletableAnalyzerResults,
			List<SampleGrouping> sampleGroupList, String sysUserId) {
		removeHandledResultsFromAnalyzerResults(deletableAnalyzerResults);

		insertResults(sampleGroupList, sysUserId);

	}

	private void removeHandledResultsFromAnalyzerResults(List<AnalyzerResults> deletableAnalyzerResults) {
		deleteAll(deletableAnalyzerResults);
	}

	private boolean insertResults(List<SampleGrouping> sampleGroupList, String sysUserId) {
		for (SampleGrouping grouping : sampleGroupList) {
			if (grouping.addSample) {
//				try {
				sampleService.insertDataWithAccessionNumber(grouping.sample);
//				} catch (LIMSRuntimeException lre) {
//					Errors errors = new BaseErrors();
//					String errorMsg = "warning.duplicate.accession";
//					errors.reject(errorMsg, new String[] { grouping.sample.getAccessionNumber() }, errorMsg);
//					saveErrors(errors);
//					return false;
//				}
			} else if (grouping.updateSample) {
				sampleService.update(grouping.sample);
			}

			String sampleId = grouping.sample.getId();

			if (grouping.addSample) {
				grouping.sampleHuman.setSampleId(sampleId);
				sampleHumanService.insert(grouping.sampleHuman);

				RecordStatus patientStatus = grouping.statusSet.getPatientRecordStatus() == null
						? RecordStatus.NotRegistered
						: null;
				RecordStatus sampleStatus = grouping.statusSet.getSampleRecordStatus() == null
						? RecordStatus.NotRegistered
						: null;
				StatusService.getInstance().persistRecordStatusForSample(grouping.sample, sampleStatus,
						grouping.patient, patientStatus, sysUserId);
			}

			if (grouping.addSampleItem) {
				grouping.sampleItem.setSample(grouping.sample);
				sampleItemService.insert(grouping.sampleItem);
			}

			for (int i = 0; i < grouping.analysisList.size(); i++) {

				Analysis analysis = grouping.analysisList.get(i);
				if (GenericValidator.isBlankOrNull(analysis.getId())) {
					analysis.setSampleItem(grouping.sampleItem);
					analysisService.insert(analysis);
				} else {
					analysisService.update(analysis);
				}

				Result result = grouping.resultList.get(i);
				if (GenericValidator.isBlankOrNull(result.getId())) {
					result.setAnalysis(analysis);
					setAnalyte(result);
					resultService.insert(result);
				} else {
					resultService.update(result);
				}

				Note note = grouping.noteList.get(i);

				if (note != null) {
					note.setReferenceId(result.getId());
					noteService.insert(note);
				}
			}
		}

		TestReflexUtil testReflexUtil = new TestReflexUtil();
		testReflexUtil.setCurrentUserId(sysUserId);
		testReflexUtil.addNewTestsToDBForReflexTests(convertGroupListToTestReflexBeans(sampleGroupList));

		return true;
	}

	private List<TestReflexBean> convertGroupListToTestReflexBeans(List<SampleGrouping> sampleGroupList) {
		List<TestReflexBean> reflexBeanList = new ArrayList<>();

		for (SampleGrouping sampleGroup : sampleGroupList) {
			if (sampleGroup.accepted) {
				for (Result result : sampleGroup.resultList) {
					TestReflexBean reflex = new TestReflexBean();
					reflex.setPatient(sampleGroup.patient);
					reflex.setTriggersToSelectedReflexesMap(sampleGroup.triggersToSelectedReflexesMap);
					reflex.setResult(result);
					reflex.setSample(sampleGroup.sample);
					reflexBeanList.add(reflex);
				}
			}
		}

		return reflexBeanList;
	}

	private void setAnalyte(Result result) {
		TestAnalyte testAnalyte = ResultUtil.getTestAnalyteForResult(result);

		if (testAnalyte != null) {
			result.setAnalyte(testAnalyte.getAnalyte());
		}
	}
}
