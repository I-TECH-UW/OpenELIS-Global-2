package oe.analyzerfilebroker.config;

import oe.analyzerfilebroker.StringLocalization;
import org.kohsuke.args4j.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {


    @Option(name="-configPath",usage="usage.configFile")
    private File confFile = new File("./BrokerConfiguration.xml");

    @Option(name="-lang", aliases={"-l"},usage="usage.language")
    private StringLocalization.supportedLocale language= null;

    @Option(name="-period", aliases = {"-p"}, usage="usage.period")
    private int period = 5;

    @Option(name="-url", aliases = {"-u"}, usage = "usage.url" )
    private String url;

    @Option(name="-accountName", aliases = {"-n"}, usage = "usage.account.name")
    private String name;

    @Option(name="-password", aliases = {"-pwd"}, usage = "usage.account.password")
    private String password;

    @Option(name="-log", usage="usage.log" )
    private String log;

    @Option(name="-logBacklog", aliases = {"-b"}, usage = "usage.log.backlog")
    private int logBacklog = 30;

    @Option(name="-console", aliases = {"-c"}, usage = "usage.console")
    private boolean console = true;

    @Option(name="-help", aliases = {"-h"},  usage = "usage.help")//, forbids = {"-test", "-printConfig"})
    private boolean help = false;

    @Option(name="-test", aliases = {"-t"}, usage = "usage.test", forbids = {"-help", "-printConfig"})
    private boolean test = false;

    @Option(name="-printConfig", aliases = {"-config"}, usage = "usage.printConfig", forbids = {"-help", "-test"})
    private boolean sample = false;

    @Option(name="-pre5__3",  usage = "usage.pre.53", forbids = {"-help", "-test"})
    private boolean pre53 = false;

    @Argument
    private List<String> arguments = new ArrayList<String>();


    public GeneralConfig parse(String[] args){
        CmdLineParser parser = new CmdLineParser(this);

        language = StringLocalization.supportedLocale.EN;
        StringLocalization.instance().setLocale(language);

        try {
            parser.parseArgument(args);
        } catch( CmdLineException e ) {
            System.err.println(e.getMessage());
            printUsage(parser);
            return null;

        }

        StringLocalization.instance().setLocale(language);

        if( help ){
            //the parser looks at the current values of the variables for default values.  help is set to the default of false
            help = false;
            printUsage(parser);
            help = true;
            return null;
        }

        if( !confFile.exists() ){
            System.out.println("Configuration file " + confFile.getAbsolutePath() + " does not exist");
            return null;
        }

        return buildConfig();
    }

    private void printUsage(CmdLineParser parser){
        System.err.println("java -jar AnalyzerFileBroker [options...] arguments...");
        System.err.println();
        parser.printUsage(new PrintWriter(System.out, true), StringLocalization.instance().getResourceBundle());
        System.err.println("  Example: java -jar AnalyzerFileBroker -accountName analyzerUser -password secret -configPath .\\BrokerConfiguration.xml -lang EN  -url http://211.118.16.04:8080/importAnalyzer");


    }
    private GeneralConfig buildConfig() {
        GeneralConfig config = new GeneralConfig();

        config.setFile(confFile);
        config.setLanguage(language);
        config.setLog(log);
        config.setLogBacklog(logBacklog);
        config.setName(name);
        config.setPassword(password);
        config.setPeriod( period);
        config.setUrl(url);
        config.setTest(test);
        config.setPrintConfig(sample);
        config.setPre53(pre53);

        return config;
    }
}
