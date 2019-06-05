package spring.service.resultvalidation;

import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.common.services.IResultSaveService;
import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.resultvalidation.bean.AnalysisItem;
import us.mn.state.health.lims.sample.valueholder.Sample;

public interface ResultValidationService {

	void persistdata(List<Result> deletableList, List<Analysis> analysisUpdateList, ArrayList<Result> resultUpdateList,
			List<AnalysisItem> resultItemList, ArrayList<Sample> sampleUpdateList, ArrayList<Note> noteUpdateList,
			IResultSaveService resultSaveService, List<IResultUpdate> updaters, String sysUserId);

}
