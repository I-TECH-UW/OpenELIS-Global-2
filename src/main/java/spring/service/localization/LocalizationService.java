package spring.service.localization;

import java.util.Locale;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.common.util.ConfigurationProperties.LOCALE;
import us.mn.state.health.lims.localization.valueholder.Localization;

public interface LocalizationService extends BaseObjectService<Localization, String> {

	@Override
	String insert(Localization localization);

	boolean languageChanged(Localization localization, String english, String french);

	void updateTestNames(Localization name, Localization reportingName);

	String getLocalizationValueByLocal(LOCALE locale, Localization localization);

	String getCurrentLocale();

	String getLocalizedValueById(String id);

	String getLocalizedValue(Localization localizedTestName);

	String getLocalizedValue(Localization localization, String locale);

	String getLocalizedValue(Localization localization, Locale locale);

}
