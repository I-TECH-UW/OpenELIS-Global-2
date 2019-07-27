package org.openelisglobal.project.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.project.valueholder.Project;

public interface ProjectService extends BaseObjectService<Project, String> {
	void getData(Project project);

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
