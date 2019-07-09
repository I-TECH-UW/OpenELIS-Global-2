package us.mn.state.health.lims.projectorganization.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import  us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.projectorganization.valueholder.ProjectOrganization;

@Component
@Transactional 
public class ProjectOrganizationDAOImpl extends BaseDAOImpl<ProjectOrganization, String> implements ProjectOrganizationDAO {
  ProjectOrganizationDAOImpl() {
    super(ProjectOrganization.class);
  }
}
