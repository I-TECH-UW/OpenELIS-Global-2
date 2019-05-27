package spring.service.localization;

import javax.annotation.PostConstruct;

import org.apache.commons.validator.GenericValidator;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.localization.dao.LocalizationDAO;
import us.mn.state.health.lims.localization.valueholder.Localization;

@Service
@DependsOn({ "springContext" })
@Scope("prototype")
public class LocalizationServiceImpl extends BaseObjectServiceImpl<Localization>
		implements LocalizationService, LocaleChangeListener {

	public enum LocalizationType {
		TEST_NAME("test name"), REPORTING_TEST_NAME("test report name"), BANNER_LABEL("Site information banner test"),
		TEST_UNIT_NAME("test unit name"), PANEL_NAME("panel name"), BILL_REF_LABEL("Billing reference_label");

		String dbLabel;

		LocalizationType(String dbLabel) {
			this.dbLabel = dbLabel;
		}

		public String getDBDescription() {
			return dbLabel;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Localization get(String id) {
		return getBaseObjectDAO().get(id).orElseThrow(() -> new ObjectNotFoundException(id, "Localization"));

	}

	private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance()
			.getPropertyValue(ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);

	@Autowired
	private static LocalizationDAO baseObjectDAO = SpringContext.getBean(LocalizationDAO.class);

	private Localization localization;

	@PostConstruct
	private void initialize() {
		SystemConfiguration.getInstance().addLocalChangeListener(this);
	}

	LocalizationServiceImpl() {
		super(Localization.class);
	}

	public LocalizationServiceImpl(String id) {
		this();
		if (!GenericValidator.isBlankOrNull(id)) {
			localization = baseObjectDAO.getLocalizationById(id);
		}
	}

	@Override
	protected LocalizationDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	public static String getLocalizationValueByLocal(ConfigurationProperties.LOCALE locale, Localization localization) {
		if (locale == ConfigurationProperties.LOCALE.FRENCH) {
			return localization.getFrench();
		} else {
			return localization.getEnglish();
		}
	}

	public static String getCurrentLocale() {
		return LANGUAGE_LOCALE;
	}

	@Override
	public void localeChanged(String locale) {
		LANGUAGE_LOCALE = locale;
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

	public static String getLocalizedValueById(String id) {
		return getLocalizedValue(baseObjectDAO.getLocalizationById(id));
	}

	public static String getLocalizedValue(Localization localization) {
		if (localization == null) {
			return "";
		}
		if (LANGUAGE_LOCALE.equals(ConfigurationProperties.LOCALE.FRENCH.getRepresentation())) {
			return localization.getFrench();
		} else {
			return localization.getEnglish();
		}
	}

	/**
	 * Checks to see if localization is needed, if so it does the update.
	 *
	 * @param french  The french localization
	 * @param english The english localization
	 * @return true if the object can be found and an update is needed. False if the
	 *         id the service was created with is not valid or the french or english
	 *         is empty or null or the french and english match what is already in
	 *         the object
	 */
	public boolean updateLocalizationIfNeeded(String english, String french) {
		if (localization == null || GenericValidator.isBlankOrNull(french) || GenericValidator.isBlankOrNull(english)) {
			return false;
		}

		if (localization == null) {
			return false;
		}

		if (english.equals(localization.getEnglish()) && french.equals(localization.getFrench())) {
			return false;
		}

		localization.setEnglish(english);
		localization.setFrench(french);
		return true;
	}

	public Localization getLocalization() {
		return localization;
	}

	public void setCurrentUserId(String currentUserId) {
		if (localization != null) {
			localization.setSysUserId(currentUserId);
		}
	}

	public static Localization createNewLocalization(String english, String french, LocalizationType type) {
		Localization localization = new Localization();
		localization.setDescription(type.getDBDescription());
		localization.setEnglish(english);
		localization.setFrench(french);
		return localization;
	}

	@Override
	public void updateData(Localization localization) {
		getBaseObjectDAO().updateData(localization);

	}

	@Override
	public Localization getLocalizationById(String id) {
		return getBaseObjectDAO().getLocalizationById(id);
	}

	@Override
	public String insert(Localization localization) {
		return (String) super.insert(localization);
	}
}
