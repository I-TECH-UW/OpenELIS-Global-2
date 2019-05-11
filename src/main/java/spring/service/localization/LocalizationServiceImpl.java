package spring.service.localization;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.localization.dao.LocalizationDAO;
import us.mn.state.health.lims.localization.valueholder.Localization;

@Service
public class LocalizationServiceImpl extends BaseObjectServiceImpl<Localization> implements LocalizationService {
	@Autowired
	protected LocalizationDAO baseObjectDAO;

	LocalizationServiceImpl() {
		super(Localization.class);
	}

	@Override
	protected LocalizationDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public boolean languageChanged(Localization localization, String english, String french) {
		if (localization == null || GenericValidator.isBlankOrNull(french) || GenericValidator.isBlankOrNull(english)) {
			return false;
		}

		if (localization == null) {
			return false;
		}

		if (english.equals(localization.getEnglish()) && french.equals(localization.getFrench())) {
			return false;
		}
		return true;
	}
}
