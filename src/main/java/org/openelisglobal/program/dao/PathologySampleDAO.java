package org.openelisglobal.program.dao;

import java.util.List;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.program.valueholder.pathology.PathologySample;
import org.openelisglobal.program.valueholder.pathology.PathologySample.PathologyStatus;

public interface PathologySampleDAO extends BaseDAO<PathologySample, Integer> {

    List<PathologySample> getWithStatus(List<PathologyStatus> statuses);

    Long getCountWithStatus(List<PathologyStatus> statuses);
}
