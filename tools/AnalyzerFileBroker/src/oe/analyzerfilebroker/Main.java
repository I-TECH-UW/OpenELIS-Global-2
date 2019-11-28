package oe.analyzerfilebroker;

import oe.analyzerfilebroker.actor.AnalyzerHandler;
import oe.analyzerfilebroker.actor.ConnectionValidator;
import oe.analyzerfilebroker.actor.FileShipper;
import oe.analyzerfilebroker.actor.PathManager;
import oe.analyzerfilebroker.config.AnalyzerConfig;
import oe.analyzerfilebroker.config.CommandLineParser;
import oe.analyzerfilebroker.config.GeneralConfig;
import oe.analyzerfilebroker.config.XMLParser;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        StringLocalization.instance().setLocale(StringLocalization.supportedLocale.FR);

        CommandLineParser commandLineParser = new CommandLineParser();
        GeneralConfig generalConfig = commandLineParser.parse(args);
        if( generalConfig == null){
            return;
        }

        boolean printConfig = generalConfig.isPrintConfig();

        ArrayList<AnalyzerConfig> analyzerConfigs = new ArrayList<AnalyzerConfig>();
        boolean parseValid = new XMLParser().parse( generalConfig, analyzerConfigs);

        boolean connectionValid = false;
        if( parseValid && !printConfig){
            FileShipper shipper = new FileShipper(generalConfig);
            PathManager.ensureQueueDirectory();
            PathManager.setFileShipper( shipper );
            connectionValid = new ConnectionValidator().validateConnection(shipper);
        }else if( !parseValid ){
            LogEvent.logInfo(this.getClass().getName(), "method unkown", StringLocalization.instance().getStringForKey("error.bad.configuration.file"));
            return;
        }else if( printConfig){
            printConfig(generalConfig, analyzerConfigs);
            return;
        }

        if( connectionValid) {
            ArrayList<AnalyzerHandler> analyzers = new ArrayList<AnalyzerHandler>(analyzerConfigs.size());
            for( AnalyzerConfig config : analyzerConfigs){
                AnalyzerHandler handler = new AnalyzerHandler(config);
                analyzers.add(handler);
            }

            for( AnalyzerHandler handler : analyzers){
                if( !handler.validateSource()){
                    LogEvent.logInfo(this.getClass().getName(), "method unkown", handler.getAnalyzerName() + ": " + handler.getSourceDirName() + " -- " + StringLocalization.instance().getStringForKey("error.analyzer.dir"));
                    return;
                }
            }

            for( AnalyzerHandler handler : analyzers){
                handler.start();
            }

            PathManager.flushQueue(AnalyzerHandler.specialQueueFiles);
            LogEvent.logInfo(this.getClass().getName(), "method unkown", StringLocalization.instance().getStringForKey("connection.valid"));
            new QuitHandler(analyzers);

        }
    }

    private static void printConfig(GeneralConfig generalConfig, ArrayList<AnalyzerConfig> analyzerConfigs) {
        LogEvent.logInfo(this.getClass().getName(), "method unkown", "");
        LogEvent.logInfo(this.getClass().getName(), "method unkown", "Configuration file name -- " + generalConfig.getFile().getName());
        LogEvent.logInfo(this.getClass().getName(), "method unkown", "Language -- " + generalConfig.getLanguage().toString());
        LogEvent.logInfo(this.getClass().getName(), "method unkown",  "URL -- " + generalConfig.getUrl());
        LogEvent.logInfo(this.getClass().getName(), "method unkown",  "OpenELIS analyzer import account name -- " + generalConfig.getName());
        LogEvent.logInfo(this.getClass().getName(), "method unkown",  "OpenELIS analyzer import account password -- " + generalConfig.getPassword());
        LogEvent.logInfo(this.getClass().getName(), "method unkown",  "Log name -- " + generalConfig.getLog() );
        LogEvent.logInfo(this.getClass().getName(), "method unkown",  "Keep log backlog in days -- " + generalConfig.getLogBacklog() );
        LogEvent.logInfo(this.getClass().getName(), "method unkown",  "Test connection and then exit -- " + generalConfig.isTest() );
        LogEvent.logInfo(this.getClass().getName(), "method unkown",  "Print configuration and then exit -- true" );
        LogEvent.logInfo(this.getClass().getName(), "method unkown",  "Installed version of OpenELIS is pre 5.3 (note, not yet implemented ignore) -- " + generalConfig.isPre53());

        LogEvent.logInfo(this.getClass().getName(), "method unkown", "\n----- Analyzers ------");
        //private int period = 5;

        for( AnalyzerConfig analyzerConfig : analyzerConfigs){

            LogEvent.logInfo(this.getClass().getName(), "method unkown", "analyzer name -- " + analyzerConfig.getName() );
            LogEvent.logInfo(this.getClass().getName(), "method unkown", "source Directory -- " + analyzerConfig.getSourceDir() );
            LogEvent.logInfo(this.getClass().getName(), "method unkown", "refresh period -- " + analyzerConfig.getPeriod() );
            LogEvent.logInfo(this.getClass().getName(), "method unkown", "file rename prefix -- " + analyzerConfig.getPrefix() );

            LogEvent.logInfo(this.getClass().getName(), "method unkown", "\n-----------\n");
        }
    }
}
