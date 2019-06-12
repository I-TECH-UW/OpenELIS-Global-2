package spring.service.datasubmission;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;

public interface TypeOfDataIndicatorService extends BaseObjectService<TypeOfDataIndicator, String> {
	void getData(TypeOfDataIndicator typeOfIndicator);

	TypeOfDataIndicator getTypeOfDataIndicator(String id);

	List<TypeOfDataIndicator> getAllTypeOfDataIndicator();
}
