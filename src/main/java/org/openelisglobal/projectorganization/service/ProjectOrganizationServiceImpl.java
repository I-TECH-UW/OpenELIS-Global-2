package org.openelisglobal.projectorganization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.projectorganization.dao.ProjectOrganizationDAO;
import org.openelisglobal.projectorganization.valueholder.ProjectOrganization;

@Service
public class ProjectOrganizationServiceImpl extends BaseObjectServiceImpl<ProjectOrganization, String> implements ProjectOrganizationService {
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
