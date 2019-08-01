package org.openelisglobal.barcode.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.barcode.valueholder.BarcodeLabelInfo;

public interface BarcodeLabelInfoService extends BaseObjectService<BarcodeLabelInfo, String> {

    BarcodeLabelInfo getDataByCode(String code);
}
