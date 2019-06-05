package spring.service.unitofmeasure;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

public interface UnitOfMeasureService extends BaseObjectService<UnitOfMeasure, String> {
	void getData(UnitOfMeasure unitOfMeasure);

	void deleteData(List unitOfMeasures);

	void updateData(UnitOfMeasure unitOfMeasure);

	boolean insertData(UnitOfMeasure unitOfMeasure);

	List getPageOfUnitOfMeasures(int startingRecNo);

	UnitOfMeasure getUnitOfMeasureById(String uomId);

	List getPreviousUnitOfMeasureRecord(String id);

	Integer getTotalUnitOfMeasureCount();

	List getNextUnitOfMeasureRecord(String id);

	UnitOfMeasure getUnitOfMeasureByName(UnitOfMeasure unitOfMeasure);

	List getAllUnitOfMeasures();

	List<UnitOfMeasure> getAllActiveUnitOfMeasures();
}
