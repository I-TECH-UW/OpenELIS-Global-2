package org.openelisglobal.testdictionary.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.testdictionary.valueholder.TestDictionary;

public interface TestDictionaryService extends BaseObjectService<TestDictionary, String> {
  TestDictionary getTestDictionaryForTestId(String testId);
}
