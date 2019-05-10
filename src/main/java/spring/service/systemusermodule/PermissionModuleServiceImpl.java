package spring.service.systemusermodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.systemusermodule.dao.PermissionModuleDAO;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;

@Service
public class PermissionModuleServiceImpl extends BaseObjectServiceImpl<PermissionModule> implements PermissionModuleService {
  @Autowired
  protected PermissionModuleDAO baseObjectDAO;

  PermissionModuleServiceImpl() {
    super(PermissionModule.class);
  }

  @Override
  protected PermissionModuleDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
