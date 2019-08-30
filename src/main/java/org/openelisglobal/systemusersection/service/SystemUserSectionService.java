package org.openelisglobal.systemusersection.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.systemusersection.valueholder.SystemUserSection;

public interface SystemUserSectionService extends BaseObjectService<SystemUserSection, String> {
    void getData(SystemUserSection systemUserSection);

    List getAllSystemUserSections();

    List getPageOfSystemUserSections(int startingRecNo);

    List getNextSystemUserSectionRecord(String id);

    Integer getTotalSystemUserSectionCount();

    List getPreviousSystemUserSectionRecord(String id);

    List getAllSystemUserSectionsBySystemUserId(int systemUserId);
}
