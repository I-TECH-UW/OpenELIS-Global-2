package spring.service.datasubmission;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;

public interface DataIndicatorService extends BaseObjectService<DataIndicator, String> {

	DataIndicator getIndicatorByTypeYearMonth(TypeOfDataIndicator type, int year, int month);

	List<DataIndicator> getIndicatorsByStatus(String status);
}
