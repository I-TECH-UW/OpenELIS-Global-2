package org.openelisglobal.systemuser.service;

import java.util.ArrayList;
import java.util.List;

import org.openelisglobal.login.service.LoginUserService;
import org.openelisglobal.login.valueholder.LoginUser;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.openelisglobal.userrole.service.UserRoleService;
import org.openelisglobal.userrole.valueholder.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginUserService loginService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private SystemUserService systemUserService;

    @Override
    @Transactional
    public void updateLoginUser(LoginUser loginUser, boolean loginUserNew, SystemUser systemUser, boolean systemUserNew,
            List<String> selectedRoles, String loggedOnUserId) {
        if (loginUserNew) {
            loginService.insert(loginUser);
        } else {
            loginService.update(loginUser);
        }

        if (systemUserNew) {
            systemUserService.insert(systemUser);
        } else {
            systemUserService.update(systemUser);
        }

        List<String> currentUserRoles = userRoleService.getRoleIdsForUser(systemUser.getId());
        List<UserRole> deletedUserRoles = new ArrayList<>();

        for (int i = 0; i < selectedRoles.size(); i++) {
            if (!currentUserRoles.contains(selectedRoles.get(i))) {
                UserRole userRole = new UserRole();
                userRole.setSystemUserId(systemUser.getId());
                userRole.setRoleId(selectedRoles.get(i));
                userRole.setSysUserId(loggedOnUserId);
                userRoleService.insert(userRole);
            } else {
                currentUserRoles.remove(selectedRoles.get(i));
            }
        }

        for (String roleId : currentUserRoles) {
            UserRole userRole = new UserRole();
            userRole.setSystemUserId(systemUser.getId());
            userRole.setRoleId(roleId);
            userRole.setSysUserId(loggedOnUserId);
            deletedUserRoles.add(userRole);
        }

        if (deletedUserRoles.size() > 0) {
            userRoleService.deleteAll(deletedUserRoles);
        }
    }

}
