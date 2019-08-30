package org.openelisglobal.renametestsection.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.localization.valueholder.Localization;
import org.openelisglobal.renametestsection.valueholder.RenameTestSection;

public interface RenameTestSectionService extends BaseObjectService<RenameTestSection, String> {
    void getData(RenameTestSection testSection);

    List getTestSections(String filter);

    RenameTestSection getTestSectionByName(RenameTestSection testSection);

    List getNextTestSectionRecord(String id);

    List getPageOfTestSections(int startingRecNo);

    Integer getTotalTestSectionCount();

    List getPreviousTestSectionRecord(String id);

    List getAllTestSections();

    RenameTestSection getTestSectionById(String id);

    Localization getLocalizationForRenameTestSection(String id);
}
