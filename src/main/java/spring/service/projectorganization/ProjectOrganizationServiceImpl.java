package spring.service.projectorganization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.projectorganization.dao.ProjectOrganizationDAO;
import us.mn.state.health.lims.projectorganization.valueholder.ProjectOrganization;

@Service
public class ProjectOrganizationServiceImpl extends BaseObjectServiceImpl<ProjectOrganization> implements ProjectOrganizationService {
	@Autowired
	protected ProjectOrganizationDAO baseObjectDAO;

	ProjectOrganizationServiceImpl() {
		super(ProjectOrganization.class);
	}

	@Override
	protected ProjectOrganizationDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}
