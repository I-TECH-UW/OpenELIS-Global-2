package us.mn.state.health.lims.analyzerimport.analyzerreaders;

import java.sql.Timestamp;
import java.util.List;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class AnalyzerReaderUtil {
	private SampleDAO sampleDAO = new SampleDAOImpl();
	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();
	private ResultDAO resultDAO = new ResultDAOImpl();

	public AnalyzerResults createAnalyzerResultFromDB(AnalyzerResults resultFromAnalyzer) {

		Sample sample = sampleDAO.getSampleByAccessionNumber(resultFromAnalyzer.getAccessionNumber());

		if( sample != null && sample.getId() != null){
			List<Analysis> analysisList = analysisDAO.getAnalysesBySampleId(sample.getId());
			String acceptedStatusId = StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance);

			for(Analysis analysis : analysisList ){
				if(analysis.getStatusId().equals(acceptedStatusId) && analysis.getTest().getId().equals(resultFromAnalyzer.getTestId())){
					List<Result> resultList = resultDAO.getResultsByAnalysis(analysis);
					if( resultList.size() > 0){
						try {
							AnalyzerResults resultFromDB = (AnalyzerResults) resultFromAnalyzer.clone();
							resultFromDB.setResult(resultList.get(resultList.size() - 1 ).getValue());
							resultFromDB.setCompleteDate(new Timestamp(analysis.getCompletedDate().getTime()));
							resultFromDB.setReadOnly(true);
							resultFromDB.setResultType(resultFromAnalyzer.getResultType());
							return resultFromDB;
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return null;
	}


}
