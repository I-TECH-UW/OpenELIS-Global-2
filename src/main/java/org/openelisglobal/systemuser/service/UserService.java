package org.openelisglobal.systemuser.service;

import java.util.List;
import java.util.Set;
import java.util.Map;

import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.login.valueholder.LoginUser;
import org.openelisglobal.resultvalidation.bean.AnalysisItem;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.openelisglobal.userrole.valueholder.UserLabUnitRoles;

public interface UserService {

    void updateLoginUser(LoginUser loginUser, boolean loginUserNew, SystemUser systemUser, boolean systemUserNew,
            List<String> selectedRoles, String loggedOnUserId);

    void saveUserLabUnitRoles(SystemUser systemUser, Map<String , Set<String>> selectedLabUnitRolesMap ,String loggedOnUserId);   
    
    UserLabUnitRoles getUserLabUnitRoles(String systemUserId);

    List<UserLabUnitRoles> getAllUserLabUnitRoles();

    List<IdValuePair> getUserTestSections(String systemUserId ,String userRole);

    List<IdValuePair> getAllDisplayUserTestsByLabUnit(String SystemUserId, String roleName);

    List<AnalysisItem> filterAnalystResultsByLabUnitRoles(String SystemUserId, List<AnalysisItem> results ,String roleName);

    List<TestResultItem> filterResultsByLabUnitRoles(String SystemUserId, List<TestResultItem> results ,String roleName);
}
