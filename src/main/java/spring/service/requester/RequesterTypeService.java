package spring.service.requester;

import java.lang.String;
import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.requester.valueholder.RequesterType;

public interface RequesterTypeService extends BaseObjectService<RequesterType> {
	RequesterType getRequesterTypeByName(String typeName);
}
