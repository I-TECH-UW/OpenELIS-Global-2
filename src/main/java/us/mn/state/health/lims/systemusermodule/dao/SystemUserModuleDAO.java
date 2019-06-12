package us.mn.state.health.lims.systemusermodule.dao;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.systemusermodule.valueholder.SystemUserModule;

public interface SystemUserModuleDAO extends PermissionModuleDAO<SystemUserModule> {

	boolean duplicateSystemUserModuleExists(SystemUserModule systemUserModule) throws LIMSRuntimeException;

}
