package spring.service.citystatezip;

import java.util.List;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.citystatezip.valueholder.CityStateZip;

public interface CityStateZipService extends BaseObjectService<CityStateZip> {

	List getAllStateCodes();
}
