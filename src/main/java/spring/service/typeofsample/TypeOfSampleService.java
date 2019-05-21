package spring.service.typeofsample;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public interface TypeOfSampleService extends BaseObjectService<TypeOfSample> {

	TypeOfSample getTypeOfSampleByDescriptionAndDomain(TypeOfSample typeOfSample, boolean b);
}
