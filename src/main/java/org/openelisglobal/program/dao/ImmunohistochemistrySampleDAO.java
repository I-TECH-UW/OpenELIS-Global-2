package org.openelisglobal.program.dao;

import java.util.List;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistrySample;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistrySample.ImmunohistochemistryStatus;

public interface ImmunohistochemistrySampleDAO extends BaseDAO<ImmunohistochemistrySample, Integer> {

    List<ImmunohistochemistrySample> getWithStatus(List<ImmunohistochemistryStatus> statuses);

    Long getCountWithStatus(List<ImmunohistochemistryStatus> statuses);
}
