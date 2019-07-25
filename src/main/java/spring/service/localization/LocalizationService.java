package spring.service.localization;

import java.util.List;
import java.util.Locale;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.localization.valueholder.Localization;

public interface LocalizationService extends BaseObjectService<Localization, String> {

	@Override
	String insert(Localization localization);

	boolean languageChanged(Localization localization, Localization oldLocalization);

	void updateTestNames(Localization name, Localization reportingName);

	String getCurrentLocale();

	String getLocalizedValueById(String id);

	List<Locale> getAllActiveLocales();

}
