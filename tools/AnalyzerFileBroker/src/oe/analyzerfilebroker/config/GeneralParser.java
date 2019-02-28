package oe.analyzerfilebroker.config;

import oe.analyzerfilebroker.StringLocalization;
import org.dom4j.Element;

import java.io.File;

import static java.lang.Integer.parseInt;

public class GeneralParser {
    private static final int DEFAULT_PERIOD = 30;
    private static final String DEFAULT_LOG_NAME = "AnalyzerConnector.log";
    private static final String DEFAULT_LOG = "." + File.separator + DEFAULT_LOG_NAME;
    private static final int DEFAULT_LOG_ROTATION = 30;

    public boolean parse(GeneralConfig generalConfig, Element xmlRoot) {
        Element generalRoot = xmlRoot.element("global");

        Element pre53 = generalRoot.element("pre53");
        if( pre53 != null && !generalConfig.isPre53()){
            generalConfig.setPre53( "true".equals(pre53.attributeValue("value").toLowerCase()));
        }

        if (generalConfig.getLanguage() == null) {
            Element language = generalRoot.element("locale");

            if (language != null) {
                String locale = language.attributeValue("value");
                StringLocalization.supportedLocale supportedLocale = null;
                if ("FR".equals(locale)) {
                    supportedLocale = StringLocalization.supportedLocale.FR;
                } else if ("EN".equals(locale)) {
                    supportedLocale = StringLocalization.supportedLocale.EN;
                }
                if( supportedLocale != null){
                    generalConfig.setLanguage(supportedLocale);
                    StringLocalization.instance().setLocale(supportedLocale);
                }
            }
        }

        if( generalConfig.getPeriod() == 0){
            Element period = generalRoot.element("period");

            if( period != null){
                try {
                    generalConfig.setPeriod(parseInt(period.attributeValue("value")));
                }catch(NumberFormatException e){
                    System.out.println( StringLocalization.instance().getStringForKey("error.period.parse"));
                }
            }

            if( generalConfig.getPeriod() == 0){
                generalConfig.setPeriod( DEFAULT_PERIOD);
            }
        }

        Element connection = generalRoot.element("connection");

        if( connection != null){
            String url = connection.attributeValue("url");
            String name = connection.attributeValue("name");
            String password = connection.attributeValue("password");
            if( generalConfig.getUrl() == null ){
                generalConfig.setUrl(url);
            }
            if( generalConfig.getName() == null ){
                generalConfig.setName(name);
            }
            if( generalConfig.getPassword() == null ){
                generalConfig.setPassword(password);
            }
        }

        if( generalConfig.getUrl() == null || generalConfig.getName() == null || generalConfig.getPassword() == null){
            System.out.println( StringLocalization.instance().getStringForKey("error.connection.specification"));
            return false;
        }

        Element log = generalRoot.element("log");

        if( log != null){
            if( generalConfig.getLog() == null){
               generalConfig.setLog(log.attributeValue("path"));
            }

            if( generalConfig.getLogBacklog() == 0){
                try {
                    generalConfig.setLogBacklog(parseInt(log.attributeValue("backlog")));
                }catch (NumberFormatException e){
                    System.out.println( StringLocalization.instance().getStringForKey("error.log.period"));
                }
            }

        }

        if( generalConfig.getLog() == null){
            generalConfig.setLog(DEFAULT_LOG);
        }

        if( generalConfig.getLogBacklog() == 0){
            generalConfig.setLogBacklog(DEFAULT_LOG_ROTATION);
        }


        return true;
    }
}
