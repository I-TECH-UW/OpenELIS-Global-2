package spring.service.result;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.result.valueholder.Result;

public interface ResultService extends BaseObjectService<Result> {

	List<Result> getResultsByAnalysis(Analysis analysis);
}
