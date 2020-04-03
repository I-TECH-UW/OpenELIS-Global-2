package org.openelisglobal.systemuser.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.systemuser.valueholder.SystemUser;

public interface SystemUserService extends BaseObjectService<SystemUser, String> {
    void getData(SystemUser systemUser);

    List<SystemUser> getPageOfSystemUsers(int startingRecNo);

    List<SystemUser> getAllSystemUsers();

    Integer getTotalSystemUserCount();

    SystemUser getDataForLoginUser(String name);

    SystemUser getUserById(String userId);
}
