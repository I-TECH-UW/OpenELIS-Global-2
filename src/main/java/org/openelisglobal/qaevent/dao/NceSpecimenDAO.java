package org.openelisglobal.qaevent.dao;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.qaevent.valueholder.NceSpecimen;

import java.util.List;

public interface NceSpecimenDAO extends BaseDAO<NceSpecimen, String> {

    List getSpecimenByNceId(String nceId) throws LIMSRuntimeException;
}
