package org.openelisglobal.testcodes.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.testcodes.valueholder.TestCodeType;

public interface TestCodeTypeService extends BaseObjectService<TestCodeType, String> {
  TestCodeType getTestCodeTypeById(String id);

  TestCodeType getTestCodeTypeByName(String name);
}
