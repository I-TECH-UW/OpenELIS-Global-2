package spring.service.testconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.localization.LocalizationService;
import spring.service.panel.PanelService;
import spring.service.rolemodule.RoleModuleService;
import spring.service.systemmodule.SystemModuleService;
import spring.service.typeofsample.TypeOfSamplePanelService;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;

@Service
public class PanelCreateServiceImpl implements PanelCreateService {

	@Autowired
	private PanelService panelService;
	@Autowired
	private RoleModuleService roleModuleService;
	@Autowired
	private SystemModuleService systemModuleService;
	@Autowired
	private TypeOfSamplePanelService typeOfSamplePanelService;
	@Autowired
	private LocalizationService localizationService;

	@Override
	@Transactional
	public void insert(Localization localization, Panel panel, SystemModule workplanModule, SystemModule resultModule,
			SystemModule validationModule, RoleModule workplanResultModule, RoleModule resultResultModule,
			RoleModule validationValidationModule, String sampleTypeId, String systemUserId) {
		localizationService.insert(localization);
		panel.setLocalization(localization);
		panelService.insert(panel);

		TypeOfSamplePanel typeOfSamplePanel = createTypeOfSamplePanel(sampleTypeId, panel, systemUserId);
		typeOfSamplePanelService.insert(typeOfSamplePanel);

		systemModuleService.insert(workplanModule);
		systemModuleService.insert(resultModule);
		systemModuleService.insert(validationModule);

		roleModuleService.insert(workplanResultModule);
		roleModuleService.insert(resultResultModule);
		roleModuleService.insert(validationValidationModule);
	}

	private TypeOfSamplePanel createTypeOfSamplePanel(String sampleTypeId, Panel panel, String userId) {
		TypeOfSamplePanel sampleTypePanel = new TypeOfSamplePanel();
		sampleTypePanel.setPanelId(panel.getId());
		sampleTypePanel.setTypeOfSampleId(sampleTypeId);
		sampleTypePanel.setSysUserId(userId);
		return sampleTypePanel;
	}

}
