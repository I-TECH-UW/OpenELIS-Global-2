package spring.service.testconfiguration;

import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

public interface PanelCreateService {

	void insert(Localization localization, Panel panel, SystemModule workplanModule, SystemModule resultModule,
			SystemModule validationModule, RoleModule workplanResultModule, RoleModule resultResultModule,
			RoleModule validationValidationModule, String sampleTypeId, String systemUserId);

}
