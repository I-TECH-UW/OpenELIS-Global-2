package us.mn.state.health.lims.systemmodule.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModuleParam;

@Component
@Transactional
public class SystemModuleParamDAOImpl extends BaseDAOImpl<SystemModuleParam> implements SystemModuleParamDAO {
  SystemModuleParamDAOImpl() {
    super(SystemModuleParam.class);
  }
}
