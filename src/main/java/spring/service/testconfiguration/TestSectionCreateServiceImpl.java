package spring.service.testconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.localization.LocalizationService;
import spring.service.rolemodule.RoleModuleService;
import spring.service.systemmodule.SystemModuleService;
import spring.service.test.TestSectionService;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;
import us.mn.state.health.lims.test.valueholder.TestSection;

@Service
public class TestSectionCreateServiceImpl implements TestSectionCreateService {

	@Autowired
	private TestSectionService testSectionService;
	@Autowired
	private LocalizationService localizationService;
	@Autowired
	private RoleModuleService roleModuleService;
	@Autowired
	private SystemModuleService systemModuleService;

	@Override
	@Transactional
	public void insertTestSection(Localization localization, TestSection testSection, SystemModule workplanModule,
			SystemModule resultModule, SystemModule validationModule, RoleModule workplanResultModule,
			RoleModule resultResultModule, RoleModule validationValidationModule) {
		localizationService.insert(localization);
		testSection.setLocalization(localization);
		testSectionService.insert(testSection);
		systemModuleService.insert(workplanModule);
		systemModuleService.insert(resultModule);
		systemModuleService.insert(validationModule);
		roleModuleService.insert(workplanResultModule);
		roleModuleService.insert(resultResultModule);
		roleModuleService.insert(validationValidationModule);
	}

}
