package org.openelisglobal.userrole.service;

import java.util.Collection;
import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.userrole.valueholder.UserRole;
import org.openelisglobal.userrole.valueholder.UserRolePK;

public interface UserRoleService extends BaseObjectService<UserRole, UserRolePK> {

    List<String> getRoleIdsForUser(String userId);

    boolean userInRole(String userId, String roleName);

    boolean userInRole(String userId, Collection<String> roleNames);

}
