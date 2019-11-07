package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.qaevent.valueholder.NceType;

import java.util.List;

public interface NceTypeService extends BaseObjectService<NceType, String> {

    List getAllNceTypes();
}
