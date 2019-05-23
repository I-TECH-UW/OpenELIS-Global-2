package spring.service.systemmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.systemmodule.dao.SystemModuleDAO;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;

@Service
public class SystemModuleServiceImpl extends BaseObjectServiceImpl<SystemModule> implements SystemModuleService {
  @Autowired
  protected SystemModuleDAO baseObjectDAO;

  SystemModuleServiceImpl() {
    super(SystemModule.class);
  }

  @Override
  protected SystemModuleDAO getBaseObjectDAO() {
    return baseObjectDAO;}

  @Override
  public String insert(SystemModule systemModule) {
	  if (baseObjectDAO.duplicateSystemModuleExists(systemModule)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + systemModule.getSystemModuleName());
	  }
	  return baseObjectDAO.insert(systemModule);
  }
}
