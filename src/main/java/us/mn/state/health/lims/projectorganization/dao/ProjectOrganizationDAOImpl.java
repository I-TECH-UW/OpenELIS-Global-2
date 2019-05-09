package us.mn.state.health.lims.projectorganization.dao;

import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.projectorganization.valueholder.ProjectOrganization;

@Component
public class ProjectOrganizationDAOImpl extends BaseDAOImpl<ProjectOrganization> implements ProjectOrganizationDAO {
  ProjectOrganizationDAOImpl() {
    super(ProjectOrganization.class);
  }
}
