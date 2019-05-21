package spring.service.analysis;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;

public interface AnalysisService extends BaseObjectService<Analysis> {

	List<Analysis> getAnalysesBySampleId(String id);

	void insert(Analysis analysis, boolean duplicateCheck);

	List<Analysis> getAnalysisByAccessionAndTestId(String accessionNumber, String testId);

	List<Analysis> getAnalysisCollectedOnExcludedByStatusId(Date date, Set<Integer> excludedStatusIds);

	List<Analysis> getAnalysesBySampleItemsExcludingByStatusIds(SampleItem sampleItem, Set<Integer> excludedStatusIds);

	List<Analysis> getAnalysesForStatusId(String status);

	List<Analysis> getAnalysesBySampleStatusIdExcludingByStatusId(String sampleStatus, Set<Integer> excludedStatusIds);

	List<Analysis> getAllAnalysisByTestAndExcludedStatus(String testId, List<Integer> excludedStatusIntList);

	void updateAnalysises(List<Analysis> cancelAnalysis, List<Analysis> newAnalysis, String sysUserId);

	List<Analysis> getAllAnalysisByTestAndStatus(String id, List<Integer> statusList);

	List<Analysis> getAllAnalysisByTestsAndStatus(List<String> nfsTestIdList, List<Integer> statusList);

	List<Analysis> getAllAnalysisByTestSectionAndStatus(String sectionId, List<Integer> statusList,
			boolean sortedByDateAndAccession);

	List<Analysis> getAnalysesBySampleItemIdAndStatusId(String sampleItemId, String canceledTestStatusId);
}
