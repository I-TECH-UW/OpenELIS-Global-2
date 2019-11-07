package org.openelisglobal.qaevent.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.qaevent.valueholder.NceSpecimen;

import java.util.List;

public interface NceSpecimenService extends BaseObjectService<NceSpecimen, String> {

    List getSpecimenByNceId(String nceId);
}
