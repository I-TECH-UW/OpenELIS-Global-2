package spring.service.result;

import java.util.List;

import us.mn.state.health.lims.common.services.registration.interfaces.IResultUpdate;
import us.mn.state.health.lims.result.action.util.ResultsUpdateDataSet;

public interface LogbookResultsPersistService {

	void persistDataSet(ResultsUpdateDataSet actionDataSet, List<IResultUpdate> updaters, String sysUserId);

}
