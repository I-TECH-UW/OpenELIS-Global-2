package spring.service.rolemodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.rolemodule.dao.RoleModuleDAO;
import us.mn.state.health.lims.rolemodule.valueholder.RoleModule;

@Service
public class RoleModuleServiceImpl extends BaseObjectServiceImpl<RoleModule> 
		implements RoleModuleService {
  
  @Autowired
  private RoleModuleDAO roleModuleDAO;
	
  public RoleModuleServiceImpl() {
    super(RoleModule.class);
  }
  
  @Override
  protected RoleModuleDAO getBaseObjectDAO() {
	return roleModuleDAO;
  }

  @Override
  @Transactional
  public void insertData(RoleModule workplanResultModule) {
    this.insertData(workplanResultModule);	
  }

}

