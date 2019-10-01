package org.openelisglobal.testconfiguration.service;

import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.dictionary.valueholder.Dictionary;
import org.openelisglobal.testconfiguration.form.ResultSelectListForm;
import org.openelisglobal.testconfiguration.form.ResultSelectListRenameForm;

import java.util.List;
import java.util.Map;

public interface ResultSelectListService {

    boolean addResultSelectList(ResultSelectListForm form, String currentUserId);

    List<Dictionary> getAllSelectListOptions();

    Map<String, List<IdValuePair>> getTestSelectDictionary();

    boolean renameOption(ResultSelectListRenameForm form,  String currentUserId);
}
