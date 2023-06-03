package org.openelisglobal.program.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.program.valueholder.pathology.PathologySample;
import org.openelisglobal.program.valueholder.pathology.PathologySample.PathologyStatus;
import org.openelisglobal.systemuser.valueholder.SystemUser;

public interface PathologySampleService extends BaseObjectService<PathologySample, Integer> {

    List<PathologySample> getWithStatus(List<PathologyStatus> statuses);

    void assignTechnician(Integer pathologySampleId, SystemUser systemUser);

    void assignPathologist(Integer pathologySampleId, SystemUser systemUser);

    Long getCountWithStatus(List<PathologyStatus> statuses);
}
