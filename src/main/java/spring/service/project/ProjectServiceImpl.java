package spring.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.valueholder.Project;

@Service
public class ProjectServiceImpl extends BaseObjectServiceImpl<Project, String> implements ProjectService {
	@Autowired
	protected ProjectDAO baseObjectDAO;

	ProjectServiceImpl() {
		super(Project.class);
	}

	@Override
	protected ProjectDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public void getData(Project project) {
		getBaseObjectDAO().getData(project);

	}

	@Override
	public List getPageOfProjects(int startingRecNo) {
		return getBaseObjectDAO().getPageOfProjects(startingRecNo);
	}

	@Override
	public List getPreviousProjectRecord(String id) {
		return getBaseObjectDAO().getPreviousProjectRecord(id);
	}

	@Override
	public Integer getTotalProjectCount() {
		return getBaseObjectDAO().getTotalProjectCount();
	}

	@Override
	public Project getProjectByLocalAbbreviation(Project project, boolean activeOnly) {
		return getBaseObjectDAO().getProjectByLocalAbbreviation(project, activeOnly);
	}

	@Override
	public List getNextProjectRecord(String id) {
		return getBaseObjectDAO().getNextProjectRecord(id);
	}

	@Override
	public Project getProjectById(String id) {
		return getBaseObjectDAO().getProjectById(id);
	}

	@Override
	public List getProjects(String filter, boolean activeOnly) {
		return getBaseObjectDAO().getProjects(filter, activeOnly);
	}

	@Override
	public List<Project> getAllProjects() {
		return getBaseObjectDAO().getAllProjects();
	}

	@Override
	public Project getProjectByName(Project project, boolean ignoreCase, boolean activeOnly) {
		return getBaseObjectDAO().getProjectByName(project, ignoreCase, activeOnly);
	}

	@Override
	public void delete(Project project) {
		Project oldData = get(project.getId());
		oldData.setIsActive(IActionConstants.NO);
		oldData.setSysUserId(project.getSysUserId());
		updateDelete(oldData);
	}

	@Override
	public String insert(Project project) {
		if (duplicateProjectExists(project)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + project.getProjectName());
		}
		return super.insert(project);
	}

	@Override
	public Project save(Project project) {
		if (duplicateProjectExists(project)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + project.getProjectName());
		}
		return super.save(project);
	}

	@Override
	public Project update(Project project) {
		if (duplicateProjectExists(project)) {
			throw new LIMSDuplicateRecordException("Duplicate record exists for " + project.getProjectName());
		}
		return super.update(project);
	}

	private boolean duplicateProjectExists(Project project) {
		return baseObjectDAO.duplicateProjectExists(project);
	}
}
