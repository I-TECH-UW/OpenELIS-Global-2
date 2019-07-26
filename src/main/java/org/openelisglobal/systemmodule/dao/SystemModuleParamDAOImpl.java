package org.openelisglobal.systemmodule.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.systemmodule.valueholder.SystemModuleParam;

@Component
@Transactional 
public class SystemModuleParamDAOImpl extends BaseDAOImpl<SystemModuleParam, String> implements SystemModuleParamDAO {
  SystemModuleParamDAOImpl() {
    super(SystemModuleParam.class);
  }
}
