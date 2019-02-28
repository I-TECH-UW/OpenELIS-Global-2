package oe.analyzerfilebroker;

import java.util.Locale;
import java.util.ResourceBundle;

public class StringLocalization {
    public enum supportedLocale {
        EN(Locale.ENGLISH),
        FR(Locale.FRENCH);

        Locale locale;
        supportedLocale(Locale locale){
            this.locale = locale;
        }

        Locale getLocale(){
            return locale;
        }
    }

    private static final StringLocalization.supportedLocale defaultLocale = supportedLocale.EN;
    private static volatile StringLocalization INSTANCE  = null;
    private static supportedLocale currentLocale = defaultLocale;
    private ResourceBundle resourceBundle;

    public static StringLocalization instance(){
        if(INSTANCE == null){
            synchronized (StringLocalization.class){
                if(INSTANCE == null){
                    INSTANCE = new StringLocalization( currentLocale);
                }
            }
        }

        return INSTANCE;
    }
    private StringLocalization(supportedLocale locale){
        setResourceBundle(locale);
    }

    private void setResourceBundle( supportedLocale locale){
        resourceBundle = ResourceBundle.getBundle("MessageResource", locale.getLocale());
    }
    public String getStringForKey(String key){
        return resourceBundle.getString(key);
    }

    public ResourceBundle getResourceBundle(){
        return resourceBundle;
    }

    public void setLocale( supportedLocale locale){
            currentLocale = locale;
            setResourceBundle(currentLocale);
    }
}
