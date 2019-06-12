package spring.service.testconfiguration;

import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

public interface SampleTypeCreateService {

	void createAndInsertSampleType(Localization localization, TypeOfSample typeOfSample, SystemModule workplanModule,
			SystemModule resultModule, SystemModule validationModule, RoleModule workplanResultModule,
			RoleModule resultResultModule, RoleModule validationValidationModule);

}
