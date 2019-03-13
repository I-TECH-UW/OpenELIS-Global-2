package oe.analyzerfilebroker.config;

import oe.analyzerfilebroker.StringLocalization;

import java.io.File;

public class GeneralConfig {
    private File file = new File("./BrokerConfiguration.xml");
    private StringLocalization.supportedLocale language= StringLocalization.supportedLocale.FR;
    private int period = 5;
    private String url;
    private String name;
    private String password;
    private String log;
    private int logBacklog = 30;
    private boolean printToConsole = true;
    private boolean test = false;
    private boolean printConfig = false;
    private boolean pre53 = false;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public StringLocalization.supportedLocale getLanguage() {
        return language;
    }

    public void setLanguage(StringLocalization.supportedLocale language) {
        this.language = language;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getLogBacklog() {
        return logBacklog;
    }

    public void setLogBacklog(int logBacklog) {
        this.logBacklog = logBacklog;
    }

    public boolean isPrintToConsole() {
        return printToConsole;
    }

    public void setPrintToConsole(boolean printToConsole) {
        this.printToConsole = printToConsole;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean isPrintConfig() {
        return printConfig;
    }

    public void setPrintConfig(boolean printConfig) {
        this.printConfig = printConfig;
    }

    public boolean isPre53() {
        return pre53;
    }

    public void setPre53(boolean pre53) {
        this.pre53 = pre53;
    }
}
