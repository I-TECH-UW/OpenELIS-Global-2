package us.mn.state.health.lims.systemusermodule.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;

@Component
public class PermissionModuleDAOImpl extends BaseDAOImpl<PermissionModule> implements PermissionModuleDAO {
  PermissionModuleDAOImpl() {
    super(PermissionModule.class);
  }
}
