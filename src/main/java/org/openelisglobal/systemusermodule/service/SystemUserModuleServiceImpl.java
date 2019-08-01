package org.openelisglobal.systemusermodule.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.systemusermodule.dao.SystemUserModuleDAO;
import org.openelisglobal.systemusermodule.valueholder.PermissionModule;
import org.openelisglobal.systemusermodule.valueholder.SystemUserModule;

@Service
public class SystemUserModuleServiceImpl extends BaseObjectServiceImpl<SystemUserModule, String>
        implements SystemUserModuleService {

    @Autowired
    SystemUserModuleDAO baseObjectDAO;

    public SystemUserModuleServiceImpl() {
        super(SystemUserModule.class);
    }

    @Override
    protected SystemUserModuleDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public void getData(SystemUserModule systemUserModule) {
        baseObjectDAO.getData(systemUserModule);
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllPermissionModules() {
        return baseObjectDAO.getAllPermissionModules();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTotalPermissionModuleCount() {
        return baseObjectDAO.getTotalPermissionModuleCount();
    }

    @Override
    @Transactional(readOnly = true)
    public List getPageOfPermissionModules(int startingRecNo) {
        return baseObjectDAO.getPageOfPermissionModules(startingRecNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List getNextPermissionModuleRecord(String id) {
        return baseObjectDAO.getNextPermissionModuleRecord(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List getAllPermissionModulesByAgentId(int systemUserId) {
        return baseObjectDAO.getAllPermissionModulesByAgentId(systemUserId);
    }

    @Override
    public boolean doesUserHaveAnyModules(int userId) {
        return baseObjectDAO.doesUserHaveAnyModules(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List getPreviousPermissionModuleRecord(String id) {
        return baseObjectDAO.getPreviousPermissionModuleRecord(id);
    }

    @Override
    public String insert(SystemUserModule systemUserModule) {
        if (duplicateSystemUserModuleExists(systemUserModule)) {
            throw new LIMSDuplicateRecordException(
                    "Duplicate record exists for " + systemUserModule.getPermissionAgentId());
        }
        return super.insert(systemUserModule);
    }

    @Override
    public SystemUserModule save(SystemUserModule systemUserModule) {
        if (duplicateSystemUserModuleExists(systemUserModule)) {
            throw new LIMSDuplicateRecordException(
                    "Duplicate record exists for " + systemUserModule.getPermissionAgentId());
        }
        return super.save(systemUserModule);
    }

    @Override
    public SystemUserModule update(SystemUserModule systemUserModule) {
        if (duplicateSystemUserModuleExists(systemUserModule)) {
            throw new LIMSDuplicateRecordException(
                    "Duplicate record exists for " + systemUserModule.getPermissionAgentId());
        }
        return super.update(systemUserModule);
    }

    private boolean duplicateSystemUserModuleExists(SystemUserModule systemUserModule) {
        return baseObjectDAO.duplicateSystemUserModuleExists(systemUserModule);
    }

    @Override
    public Set<String> getAllPermittedPagesFromAgentId(int roleId) {
        Set<String> permittedPages = new HashSet<>();
        List<SystemUserModule> permissionModules = getAllPermissionModulesByAgentId((roleId));

        for (PermissionModule permissionModule : permissionModules) {
            permittedPages.add(permissionModule.getSystemModule().getSystemModuleName());
        }
        return permittedPages;
    }

}
