package spring.service.localization;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.common.util.ConfigurationProperties.LOCALE;
import us.mn.state.health.lims.localization.valueholder.Localization;

public interface LocalizationService extends BaseObjectService<Localization, String> {

	String insert(Localization localization);

	void updateData(Localization localization);

	Localization getLocalizationById(String id);

	boolean languageChanged(Localization localization, String english, String french);

}
