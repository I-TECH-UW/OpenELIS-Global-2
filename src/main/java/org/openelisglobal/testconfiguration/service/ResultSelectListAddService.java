package org.openelisglobal.testconfiguration.service;

import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.testconfiguration.form.ResultSelectListForm;

import java.util.List;
import java.util.Map;

public interface ResultSelectListAddService {

    boolean addResultSelectList(ResultSelectListForm form, String currentUserId);

    Map<String, List<IdValuePair>> getTestSelectDictionary();
}
