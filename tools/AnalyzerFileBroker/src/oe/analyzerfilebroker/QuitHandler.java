package oe.analyzerfilebroker;


import oe.analyzerfilebroker.actor.AnalyzerHandler;

import java.util.ArrayList;

public class QuitHandler {

    public QuitHandler(final ArrayList<AnalyzerHandler> analyzers){
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println(StringLocalization.instance().getStringForKey("shutdown.start"));
                for( AnalyzerHandler analyzer : analyzers){
                    analyzer.kill();
                }
                System.out.println(StringLocalization.instance().getStringForKey("shutdown.finished"));
            }
        });
    }
}
