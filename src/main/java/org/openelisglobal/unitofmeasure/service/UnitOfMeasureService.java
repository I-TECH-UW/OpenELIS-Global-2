package org.openelisglobal.unitofmeasure.service;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.unitofmeasure.valueholder.UnitOfMeasure;

public interface UnitOfMeasureService extends BaseObjectService<UnitOfMeasure, String> {

    UnitOfMeasure getUnitOfMeasureById(String uomId);

    UnitOfMeasure getUnitOfMeasureByName(UnitOfMeasure unitOfMeasure);

    void refreshNames();

}
