package spring.service.unitofmeasure;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

public interface UnitOfMeasureService extends BaseObjectService<UnitOfMeasure, String> {

	UnitOfMeasure getUnitOfMeasureById(String uomId);

	UnitOfMeasure getUnitOfMeasureByName(UnitOfMeasure unitOfMeasure);

	void refreshNames();

}
