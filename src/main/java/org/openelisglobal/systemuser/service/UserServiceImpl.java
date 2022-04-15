package org.openelisglobal.systemuser.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.services.DisplayListService.ListType;
import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.login.service.LoginUserService;
import org.openelisglobal.login.valueholder.LoginUser;
import org.openelisglobal.resultvalidation.bean.AnalysisItem;
import org.openelisglobal.role.service.RoleService;
import org.openelisglobal.systemuser.valueholder.SystemUser;
import org.openelisglobal.test.beanItems.TestResultItem;
import org.openelisglobal.test.valueholder.Test;
import org.openelisglobal.typeofsample.service.TypeOfSampleService;
import org.openelisglobal.userrole.service.UserRoleService;
import org.openelisglobal.userrole.valueholder.UserLabUnitRoles;
import org.openelisglobal.userrole.valueholder.LabUnitRoleMap;
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
    @Autowired
    private RoleService roleService;
    @Autowired
    private TypeOfSampleService typeOfSampleService;


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
        
        updateUserRoles(selectedRoles, systemUser, loggedOnUserId, false);
    }

    @Override
    @Transactional
    public void saveUserLabUnitRoles(SystemUser systemUser, Map<String, Set<String>> selectedLabUnitRolesMap,
            String loggedOnUserId) {
        UserLabUnitRoles userLabUnitRoles = userRoleService.getUserLabUnitRoles(systemUser.getId());
        Set<LabUnitRoleMap> labUnitRoleMaps;
        if (userLabUnitRoles == null) {
            userLabUnitRoles = new UserLabUnitRoles();
            userLabUnitRoles.setId(Integer.valueOf(systemUser.getId()));
            labUnitRoleMaps = new HashSet<>();
        } else {
            labUnitRoleMaps = userLabUnitRoles.getLabUnitRoleMap();
            for (LabUnitRoleMap roleMap : labUnitRoleMaps) {
                userRoleService.deleteLabUnitRoleMap(roleMap);
            }
            labUnitRoleMaps.clear();
        }
        Set<String> labUnitRoles = new HashSet<>();
        for (String labUnit : selectedLabUnitRolesMap.keySet()) {
            if (StringUtils.isNotEmpty(labUnit)) {
                LabUnitRoleMap labUnitRoleMap = new LabUnitRoleMap();
                labUnitRoleMap.setLabUnit(labUnit);
                labUnitRoleMap.setRoles(selectedLabUnitRolesMap.get(labUnit));
                labUnitRoleMaps.add(labUnitRoleMap);
                for (String role : selectedLabUnitRolesMap.get(labUnit)) {
                    labUnitRoles.add(role);
                }
            }
            
        }
        userLabUnitRoles.setLabUnitRoleMap(labUnitRoleMaps);
        userRoleService.saveOrUpdateUserLabUnitRoles(userLabUnitRoles);
        updateUserRoles(labUnitRoles.stream().collect(Collectors.toList()), systemUser, loggedOnUserId, true);
    }
    
    @Override
    @Transactional
    public UserLabUnitRoles getUserLabUnitRoles(String systemUserId) {
        return userRoleService.getUserLabUnitRoles(systemUserId);
    }

    @Override
    @Transactional
    public List<UserLabUnitRoles> getAllUserLabUnitRoles() {
        return userRoleService.getAllUserLabUnitRoles();
    }

    private void updateUserRoles(List<String> selectedRoles, SystemUser systemUser, String loggedOnUserId,
            Boolean isLabRole) {
        List<String> currentUserRoles = userRoleService.getRoleIdsForUser(systemUser.getId());
        List<UserRole> deletedUserRoles = new ArrayList<>();
        if (isLabRole) {
            for (String role : currentUserRoles) {
                selectedRoles.add(role);
            }
        }
        
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

    @Override
    public List<IdValuePair> getUserTestSections(String systemUserId, String roleId) {
        List<String> userLabUnits = new ArrayList<>();
        UserLabUnitRoles userLabRoles = getUserLabUnitRoles(systemUserId);
        if (userLabRoles != null) {
            userLabRoles.getLabUnitRoleMap().forEach(roles -> {
                if (roles.getRoles().contains(roleId)) {
                    userLabUnits.add(roles.getLabUnit());
                }
            });
        }
        List<IdValuePair> allTestSections = DisplayListService.getInstance().getList(ListType.TEST_SECTION);
        List<IdValuePair> userTestSections = allTestSections.stream().filter(test -> userLabUnits.contains(test.getId())).collect(Collectors.toList());
        return userTestSections;
    }

    @Override
    public List<TestResultItem> filterResultsByLabUnitRoles(String SystemUserId, List<TestResultItem> results ,String roleName) {
        String resultsRoleId = roleService.getRoleByName(roleName).getId();
        List<IdValuePair> testSections = getUserTestSections(SystemUserId, resultsRoleId);
        List<String> testUnitIds = new ArrayList<>();
        if (testSections != null) {
            testSections.forEach(test -> testUnitIds.add(test.getId()));
        }
        
        List<Test> allTests = typeOfSampleService.getAllActiveTestsByTestUnit(true, testUnitIds);
        List<String> allTestsIds = new ArrayList<>();
        allTests.forEach(test -> allTestsIds.add(test.getId()));
        return results.stream().filter(result -> allTestsIds.contains(result.getTestId())).collect(Collectors.toList());
    }

    @Override
    public List<IdValuePair> getAllDisplayUserTestsByLabUnit(String SystemUserId, String roleName) {
        String resultsRoleId = roleService.getRoleByName(roleName).getId();
        List<IdValuePair> testSections = getUserTestSections(SystemUserId, resultsRoleId);
        List<String> testUnitIds = new ArrayList<>();
        if (testSections != null) {
            testSections.forEach(test -> testUnitIds.add(test.getId()));
        }
        
        List<Test> allTests = typeOfSampleService.getAllActiveTestsByTestUnit(true, testUnitIds);
        List<String> allTestsIds = new ArrayList<>();
        allTests.forEach(test -> allTestsIds.add(test.getId()));
        
        List<IdValuePair> allDisplayUserTests = DisplayListService.getInstance()
                .getListWithLeadingBlank(DisplayListService.ListType.ALL_TESTS);
        return allDisplayUserTests.stream().filter(test -> allTestsIds.contains(test.getId())).collect(Collectors.toList());
    }

    @Override
    public List<AnalysisItem> filterAnalystResultsByLabUnitRoles(String SystemUserId, List<AnalysisItem> results ,String roleName) {
        String resultsRoleId = roleService.getRoleByName(roleName).getId();
        List<IdValuePair> testSections = getUserTestSections(SystemUserId, resultsRoleId);
        List<String> testUnitIds = new ArrayList<>();
        if (testSections != null) {
            testSections.forEach(test -> testUnitIds.add(test.getId()));
        }
        
        List<Test> allTests = typeOfSampleService.getAllActiveTestsByTestUnit(true, testUnitIds);
        List<String> allTestsIds = new ArrayList<>();
        allTests.forEach(test -> allTestsIds.add(test.getId()));
        return results.stream().filter(result -> allTestsIds.contains(result.getTestId())).collect(Collectors.toList());
    }
}
