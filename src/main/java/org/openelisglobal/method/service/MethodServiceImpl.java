package org.openelisglobal.method.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


import org.openelisglobal.common.action.IActionConstants;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.method.dao.MethodDAO;
import org.openelisglobal.method.valueholder.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.DependsOn;


@Service
@DependsOn({ "springContext" })
public class MethodServiceImpl extends BaseObjectServiceImpl<Method, String> implements MethodService {
    @Autowired
    protected MethodDAO baseObjectDAO;

    private Map<String, String> testUnitIdToNameMap;


    MethodServiceImpl() {
        super(Method.class);
    }

    @Override
    protected MethodDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Method> getMethods(String filter) {
        return getBaseObjectDAO().getMethods(filter);
    }

    @Override
    public void delete(Method method) {
        Method oldMethod = get(method.getId());
        oldMethod.setIsActive(IActionConstants.NO);
        oldMethod.setSysUserId(method.getSysUserId());
        updateDelete(oldMethod);
    }

    @Override
    public String insert(Method method) {
        if (getBaseObjectDAO().duplicateMethodExists(method)) {
            throw new LIMSDuplicateRecordException("Duplicate record exists for " + method.getMethodName());
        }
        return super.insert(method);
    }

    @Override
    public Method save(Method method) {
        if (getBaseObjectDAO().duplicateMethodExists(method)) {
            throw new LIMSDuplicateRecordException("Duplicate record exists for " + method.getMethodName());
        }
        return super.save(method);
    }

    @Override
    public Method update(Method method) {
        if (getBaseObjectDAO().duplicateMethodExists(method)) {
            throw new LIMSDuplicateRecordException("Duplicate record exists for " + method.getMethodName());
        }
        return super.update(method);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Method> getAllInActiveMethods() {
        return getBaseObjectDAO().getAllInActiveMethods();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Method> getAllMethods() {
        return baseObjectDAO.getAllMethods();
    }

    @Override
    @Transactional(readOnly = true)
    public Method getMethodById(String methodId) {
        return getBaseObjectDAO().getMethodById(methodId);
    }

    @Override
    public void refreshNames() {
        methodNamesChanged();
    }

    public void methodNamesChanged() {
        createMethodToNameMap();
    }

    private synchronized void createMethodToNameMap() {
        testUnitIdToNameMap = new HashMap<>();

        List<Method> methods = baseObjectDAO.getAllMethods();

        for (Method method : methods) {
            testUnitIdToNameMap.put(method.getId(), buildMethodName(method).replace("\n", " "));
        }
    }

    private String buildMethodName(Method method) {
        return method.getLocalization().getLocalizedValue();
    }


}
