package org.openelisglobal.result.service;

import java.util.List;

import org.openelisglobal.common.services.registration.interfaces.IResultUpdate;
import org.openelisglobal.result.action.util.ResultsUpdateDataSet;

public interface LogbookResultsPersistService {

    void persistDataSet(ResultsUpdateDataSet actionDataSet, List<IResultUpdate> updaters, String sysUserId);

}
