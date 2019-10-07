package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.qaevent.valueholder.NceCategory;

import java.util.List;

public interface NceCategoryService extends BaseObjectService<NceCategory, String> {
    List getAllNceCategories();
}
