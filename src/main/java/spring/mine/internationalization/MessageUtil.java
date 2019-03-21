package spring.mine.internationalization;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;

@Component
public class MessageUtil {

	private static MessageUtil instance;
	private static String STRING_KEY_SUFFIX = null;

	@Autowired
	MessageSource messageSource;

	@PostConstruct
	public void registerInstance() {
		// Makes this a singelton when spring automatically creates this object
		instance = this;
	}

	/**
	 * @param key - message key
	 * @return - message for key in current locale
	 */
	public static String getMessage(String key) {
		return getMessage(key, null, LocaleContextHolder.getLocale());
	}

	/**
	 * @param key    - message key
	 * @param locale - specific locale to check
	 * @return - message for key in locale
	 */
	public static String getMessage(String key, Locale locale) {
		return getMessage(key, null, locale);
	}

	/**
	 * @param key  - message key
	 * @param args - args to pass into message
	 * @return - message for key in current locale
	 */
	public static String getMessage(String key, String[] args) {
		return getMessage(key, args, LocaleContextHolder.getLocale());
	}

	/**
	 * @param key - message key
	 * @param arg - single arg to pass in
	 * @return - message for key in current locale
	 */
	public static String getMessage(String key, String arg) {
		return getMessage(key, new String[] { arg }, LocaleContextHolder.getLocale());
	}

	/**
	 * @param key    - message key
	 * @param args   - args to pass into message
	 * @param locale - specific locale to check
	 * @return - message for key in locale
	 */
	public static String getMessage(String key, String[] args, Locale locale) {
		String msg;
		try {
			msg = instance.messageSource.getMessage(key, args, locale);
		} catch (NullPointerException e) {
			msg = key;
			LogEvent.logError("MessageUtil", "getMessage()", e.toString());
		} catch (NoSuchMessageException e) {
			msg = key;
			LogEvent.logWarn("MessageUtil", "getMessage()", e.toString());
		}
		return msg;
	}

	/**
	 * @param key - message key
	 * @return - message for contextual key in current locale
	 */
	public static String getContextualMessage(String messageKey) {
		return getMessage(getContextualKey(messageKey));
	}

	public static String getContextualKey(String key) {
		if (null == key) {
			return null;
		}

		// Note that if there is no suffix then the suffix key will be the same
		// as the message key
		// and the first search will be successful, there is no reason to test
		// for the suffix
		String contextualKey = key + getSuffix();
		String suffixedValue = getMessage(contextualKey);

		if (GenericValidator.isBlankOrNull(suffixedValue) || contextualKey.equals(suffixedValue)) {
			LogEvent.logWarn("MessageUtil", "getContextualKey()", "contextual key not found, using non-contextul key");
			return key;
		}
		return contextualKey;
	}

	private static String getSuffix() {
		if (STRING_KEY_SUFFIX == null) {
			STRING_KEY_SUFFIX = ConfigurationProperties.getInstance().getPropertyValue(Property.StringContext);
			if (!GenericValidator.isBlankOrNull(STRING_KEY_SUFFIX)) {
				STRING_KEY_SUFFIX = "." + STRING_KEY_SUFFIX.trim();
			}
		}

		return STRING_KEY_SUFFIX;
	}
}
