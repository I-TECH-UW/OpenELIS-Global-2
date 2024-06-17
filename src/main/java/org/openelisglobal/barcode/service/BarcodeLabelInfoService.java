package org.openelisglobal.barcode.service;

import org.openelisglobal.barcode.valueholder.BarcodeLabelInfo;
import org.openelisglobal.common.service.BaseObjectService;

public interface BarcodeLabelInfoService extends BaseObjectService<BarcodeLabelInfo, String> {

  BarcodeLabelInfo getDataByCode(String code);
}
