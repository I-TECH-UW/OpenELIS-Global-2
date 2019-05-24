package spring.service.datasubmission;

import java.lang.String;
import java.util.List;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;

public interface TypeOfDataIndicatorService extends BaseObjectService<TypeOfDataIndicator> {
	void getData(TypeOfDataIndicator typeOfIndicator);

	void updateData(TypeOfDataIndicator typeOfIndicator);

	boolean insertData(TypeOfDataIndicator typeOfIndicator);

	TypeOfDataIndicator getTypeOfDataIndicator(String id);

	List<TypeOfDataIndicator> getAllTypeOfDataIndicator();
}
