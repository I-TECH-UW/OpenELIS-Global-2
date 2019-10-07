package org.openelisglobal.qaevent.dao;

import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.qaevent.valueholder.NceCategory;

import java.util.List;

public interface NceCategoryDAO extends BaseDAO<NceCategory, String> {

    List getAllNceCategory() throws LIMSRuntimeException;
}
