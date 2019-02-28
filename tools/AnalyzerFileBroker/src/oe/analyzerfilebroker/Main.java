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
            System.out.println(StringLocalization.instance().getStringForKey("error.bad.configuration.file"));
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
                    System.out.println(handler.getAnalyzerName() + ": " + handler.getSourceDirName() + " -- " + StringLocalization.instance().getStringForKey("error.analyzer.dir"));
                    return;
                }
            }

            for( AnalyzerHandler handler : analyzers){
                handler.start();
            }

            PathManager.flushQueue(AnalyzerHandler.specialQueueFiles);
            System.out.println(StringLocalization.instance().getStringForKey("connection.valid"));
            new QuitHandler(analyzers);

        }
    }

    private static void printConfig(GeneralConfig generalConfig, ArrayList<AnalyzerConfig> analyzerConfigs) {
        System.out.println("");
        System.out.println("Configuration file name -- " + generalConfig.getFile().getName());
        System.out.println("Language -- " + generalConfig.getLanguage().toString());
        System.out.println( "URL -- " + generalConfig.getUrl());
        System.out.println( "OpenELIS analyzer import account name -- " + generalConfig.getName());
        System.out.println( "OpenELIS analyzer import account password -- " + generalConfig.getPassword());
        System.out.println( "Log name -- " + generalConfig.getLog() );
        System.out.println( "Keep log backlog in days -- " + generalConfig.getLogBacklog() );
        System.out.println( "Test connection and then exit -- " + generalConfig.isTest() );
        System.out.println( "Print configuration and then exit -- true" );
        System.out.println( "Installed version of OpenELIS is pre 5.3 (note, not yet implemented ignore) -- " + generalConfig.isPre53());

        System.out.println("\n----- Analyzers ------");
        //private int period = 5;

        for( AnalyzerConfig analyzerConfig : analyzerConfigs){

            System.out.println("analyzer name -- " + analyzerConfig.getName() );
            System.out.println("source Directory -- " + analyzerConfig.getSourceDir() );
            System.out.println("refresh period -- " + analyzerConfig.getPeriod() );
            System.out.println("file rename prefix -- " + analyzerConfig.getPrefix() );

            System.out.println("\n-----------\n");
        }
    }
}
