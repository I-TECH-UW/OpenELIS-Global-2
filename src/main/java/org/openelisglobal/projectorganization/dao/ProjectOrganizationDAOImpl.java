package org.openelisglobal.projectorganization.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.projectorganization.valueholder.ProjectOrganization;

@Component
@Transactional
public class ProjectOrganizationDAOImpl extends BaseDAOImpl<ProjectOrganization, String>
        implements ProjectOrganizationDAO {
    ProjectOrganizationDAOImpl() {
        super(ProjectOrganization.class);
    }
}
