package spring.mine.internationalization;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import us.mn.state.health.lims.common.log.LogEvent;

@Component
public class MessageUtil {

	public static MessageUtil instance;

	@Autowired
	MessageSource messageSource;

	@PostConstruct
	public void registerInstance() {
		//Makes this a singelton when spring automatically creates this object
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
	 * @param key - message key
	 * @param locale - specific locale to check
	 * @return - message for key in locale
	 */
	public static String getMessage(String key, Locale locale) {
		return getMessage(key, null, locale);
	}

	/**
	 * @param key - message key
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
	 * @param key - message key
	 * @param args - args to pass into message
	 * @param locale - specific locale to check
	 * @return - message for key in locale
	 */
	public static String getMessage(String key, String[] args, Locale locale) {
		String msg;
		try {
			msg = instance.messageSource.getMessage(key, args, locale);
		} catch (NullPointerException e) {
			msg = null;
			LogEvent.logError("MessageUtil", "getMessage()", e.toString());
		} catch (NoSuchMessageException e) {
			msg = null;
			LogEvent.logWarn("MessageUtil", "getMessage()", e.toString());
		}
		return msg;
	}
}
