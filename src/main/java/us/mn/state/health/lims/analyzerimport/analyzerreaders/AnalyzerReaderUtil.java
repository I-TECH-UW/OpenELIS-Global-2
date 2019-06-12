package us.mn.state.health.lims.analyzerimport.analyzerreaders;

import java.sql.Timestamp;
import java.util.List;

import spring.service.analysis.AnalysisService;
import spring.service.result.ResultService;
import spring.service.sample.SampleService;
import spring.util.SpringContext;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.AnalysisStatus;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.sample.valueholder.Sample;

public class AnalyzerReaderUtil {
//	private SampleDAO sampleDAO = new SampleDAOImpl();
//	private AnalysisDAO analysisDAO = new AnalysisDAOImpl();
//	private ResultDAO resultDAO = new ResultDAOImpl();
	
	protected SampleService sampleService = SpringContext.getBean(SampleService.class);
	protected AnalysisService analysisService = SpringContext.getBean(AnalysisService.class);
	protected ResultService resultService = SpringContext.getBean(ResultService.class);
	
	public AnalyzerResults createAnalyzerResultFromDB(AnalyzerResults resultFromAnalyzer) {

		Sample sample = sampleService.getSampleByAccessionNumber(resultFromAnalyzer.getAccessionNumber());

		if( sample != null && sample.getId() != null){
			List<Analysis> analysisList = analysisService.getAnalysesBySampleId(sample.getId());
			String acceptedStatusId = StatusService.getInstance().getStatusID(AnalysisStatus.TechnicalAcceptance);

			for(Analysis analysis : analysisList ){
				if(analysis.getStatusId().equals(acceptedStatusId) && analysis.getTest().getId().equals(resultFromAnalyzer.getTestId())){
					List<Result> resultList = resultService.getResultsByAnalysis(analysis);
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
