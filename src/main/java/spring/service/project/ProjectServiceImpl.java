package spring.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.valueholder.Project;

@Service
public class ProjectServiceImpl extends BaseObjectServiceImpl<Project> implements ProjectService {
  @Autowired
  protected ProjectDAO baseObjectDAO;

  ProjectServiceImpl() {
    super(Project.class);
  }

  @Override
  protected ProjectDAO getBaseObjectDAO() {
    return baseObjectDAO;}
}
