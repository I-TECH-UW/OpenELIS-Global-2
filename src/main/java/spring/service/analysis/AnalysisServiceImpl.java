package spring.service.analysis;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

@Service
public class AnalysisServiceImpl extends BaseObjectServiceImpl<Analysis> implements AnalysisService {
	@Autowired
	protected AnalysisDAO baseObjectDAO;

	AnalysisServiceImpl() {
		super(Analysis.class);
	}

	@Override
	protected AnalysisDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleId(String id) {
		return baseObjectDAO.getAnalysesBySampleId(id);
	}

	@Override
	@Transactional
	public void insert(Analysis analysis, boolean duplicateCheck) {
		baseObjectDAO.insertData(analysis, duplicateCheck);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysisByAccessionAndTestId(String accessionNumber, String testId) {
		return baseObjectDAO.getAnalysisByAccessionAndTestId(accessionNumber, testId);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysisCollectedOnExcludedByStatusId(Date date, Set<Integer> excludedStatusIds) {
		return baseObjectDAO.getAnalysisCollectedOnExcludedByStatusId(date, excludedStatusIds);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleItemsExcludingByStatusIds(SampleItem sampleItem,
			Set<Integer> excludedStatusIds) {
		return baseObjectDAO.getAnalysesBySampleItemsExcludingByStatusIds(sampleItem, excludedStatusIds);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesForStatusId(String status) {
		return baseObjectDAO.getAllMatching("statusId", status);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleStatusIdExcludingByStatusId(String sampleStatus,
			Set<Integer> excludedStatusIds) {
		return baseObjectDAO.getAnalysesBySampleStatusIdExcludingByStatusId(sampleStatus, excludedStatusIds);
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestAndExcludedStatus(String testId, List<Integer> excludedStatusIntList) {
		return baseObjectDAO.getAllAnalysisByTestAndExcludedStatus(testId, excludedStatusIntList);
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
		return baseObjectDAO.getAllAnalysisByTestAndStatus(id, statusList);
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestsAndStatus(List<String> nfsTestIdList, List<Integer> statusList) {
		return baseObjectDAO.getAllAnalysisByTestsAndStatus(nfsTestIdList, statusList);
	}

	@Override
	@Transactional
	public List<Analysis> getAllAnalysisByTestSectionAndStatus(String sectionId, List<Integer> statusList,
			boolean sortedByDateAndAccession) {
		return baseObjectDAO.getAllAnalysisByTestSectionAndStatus(sectionId, statusList, sortedByDateAndAccession);
	}

	@Override
	@Transactional
	public List<Analysis> getAnalysesBySampleItemIdAndStatusId(String sampleItemId, String canceledTestStatusId) {
		return baseObjectDAO.getAnalysesBySampleItemIdAndStatusId(sampleItemId, canceledTestStatusId);
	}
}
