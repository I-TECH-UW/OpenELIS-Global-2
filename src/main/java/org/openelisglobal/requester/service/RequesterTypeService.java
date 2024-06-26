package org.openelisglobal.requester.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.requester.valueholder.RequesterType;

public interface RequesterTypeService extends BaseObjectService<RequesterType, String> {
    RequesterType getRequesterTypeByName(String typeName);
}
