package org.openelisglobal.qaevent.dao;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.qaevent.valueholder.NceType;

import java.util.List;

public interface NceTypeDAO extends BaseDAO<NceType, String> {

    List getAllNceType() throws LIMSRuntimeException;
}
