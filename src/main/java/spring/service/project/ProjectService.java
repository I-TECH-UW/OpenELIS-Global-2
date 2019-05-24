package spring.service.project;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.project.valueholder.Project;

public interface ProjectService extends BaseObjectService<Project> {
	void getData(Project project);

	void deleteData(List projects);

	void updateData(Project project);

	boolean insertData(Project project);

	List getPageOfProjects(int startingRecNo);

	List getPreviousProjectRecord(String id);

	Integer getTotalProjectCount();

	Project getProjectByLocalAbbreviation(Project project, boolean activeOnly);

	List getNextProjectRecord(String id);

	Project getProjectById(String id);

	List getProjects(String filter, boolean activeOnly);

	List<Project> getAllProjects();

	Project getProjectByName(Project project, boolean ignoreCase, boolean activeOnly);
}
