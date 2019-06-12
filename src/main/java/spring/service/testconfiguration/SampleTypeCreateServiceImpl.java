package spring.service.testconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.localization.LocalizationService;
import spring.service.rolemodule.RoleModuleService;
import spring.service.systemmodule.SystemModuleService;
import spring.service.typeofsample.TypeOfSampleService;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;

@Service
public class SampleTypeCreateServiceImpl implements SampleTypeCreateService {

	@Autowired
	private TypeOfSampleService typeOfSampleService;
	@Autowired
	private RoleModuleService roleModuleService;
	@Autowired
	private SystemModuleService systemModuleService;
	@Autowired
	private LocalizationService localizationService;

	@Override
	@Transactional
	public void createAndInsertSampleType(Localization localization, TypeOfSample typeOfSample,
			SystemModule workplanModule, SystemModule resultModule, SystemModule validationModule,
			RoleModule workplanResultModule, RoleModule resultResultModule, RoleModule validationValidationModule) {
		localizationService.insert(localization);
		typeOfSample.setLocalization(localization);
		typeOfSampleService.insert(typeOfSample);
		systemModuleService.insert(workplanModule);
		systemModuleService.insert(resultModule);
		systemModuleService.insert(validationModule);
		roleModuleService.insert(workplanResultModule);
		roleModuleService.insert(resultResultModule);
		roleModuleService.insert(validationValidationModule);
	}

}
