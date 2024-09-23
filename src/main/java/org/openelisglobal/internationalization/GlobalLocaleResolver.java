package org.openelisglobal.internationalization;

import java.util.Locale;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openelisglobal.common.util.SystemConfiguration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.i18n.AbstractLocaleContextResolver;

public class GlobalLocaleResolver extends AbstractLocaleContextResolver {

    private Locale defaultLocale;
    private Locale currentLocale;
    private TimeZone timeZone;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        if (currentLocale == null) {
            return defaultLocale;
        }
        return currentLocale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        if (!locale.equals(currentLocale)) {
            currentLocale = locale;
            SystemConfiguration.getInstance().setDefaultLocale(locale.getLanguage());
        }
        currentLocale = locale;
    }

    @Override
    public void setDefaultLocale(Locale locale) {
        defaultLocale = locale;
    }

    @Override
    public LocaleContext resolveLocaleContext(HttpServletRequest request) {
        return new TimeZoneAwareLocaleContext() {
            @Override
            public Locale getLocale() {
                if (currentLocale == null) {
                    currentLocale = determineDefaultLocale();
                }
                return currentLocale;
            }

            @Override
            @Nullable
            public TimeZone getTimeZone() {
                if (timeZone == null) {
                    timeZone = determineDefaultTimeZone(request);
                }
                return timeZone;
            }
        };
    }

    @Override
    public void setLocaleContext(HttpServletRequest request, HttpServletResponse response,
            LocaleContext localeContext) {
        Locale locale = null;
        if (localeContext != null) {
            locale = localeContext.getLocale();
        }
        currentLocale = locale;
    }

    public Locale determineDefaultLocale() {
        return defaultLocale;
    }

    /**
     * Determine the default time zone for the given request, Called if no TimeZone
     * session attribute has been found.
     *
     * <p>
     * The default implementation returns the specified default time zone, if any,
     * or {@code null} otherwise.
     *
     * @param request the request to resolve the time zone for
     * @return the default time zone (or {@code null} if none defined)
     * @see #setDefaultTimeZone
     */
    @Nullable
    protected TimeZone determineDefaultTimeZone(HttpServletRequest request) {
        return getDefaultTimeZone();
    }
}
