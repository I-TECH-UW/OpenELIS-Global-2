package oe.analyzerfilebroker.config;

import oe.analyzerfilebroker.StringLocalization;
import org.dom4j.Element;
import java.util.List;

public class AnalyzerParser {

    public boolean parse(GeneralConfig generalConfig, List<AnalyzerConfig> analyzerConfigs, Element xmlRoot){
        Element analyzersRoot = xmlRoot.element("analyzers");

        if( analyzersRoot == null){
            LogEvent.logInfo(this.getClass().getName(), "method unkown", StringLocalization.instance().getStringForKey("error.missing.analyzers"));
            return false;
        }
        List<Element> analyzerList = analyzersRoot.elements("analyzer");

        if( analyzerList.size() == 0){
            LogEvent.logInfo(this.getClass().getName(), "method unkown", StringLocalization.instance().getStringForKey("error.missing.analyzer"));
            return false;
        }

        for(Element analyzer : analyzerList){
            AnalyzerConfig config = new AnalyzerConfig();
            analyzerConfigs.add(config);

            //    <analyzer name = "bob" sourceDir="D:/analyzerSource/Bob" prefix="bb"  /
            config.setName(analyzer.attributeValue("name"));
            config.setPrefix(analyzer.attributeValue("prefix"));
            config.setSourceDir(analyzer.attributeValue("sourceDir"));

            String customPeriod = analyzer.attributeValue("period");

            if( customPeriod != null){
                try{
                    Integer intPeriod = Integer.parseInt(customPeriod);
                    if( intPeriod < 1){
                        LogEvent.logInfo(this.getClass().getName(), "method unkown", config.getName() + ": " +StringLocalization.instance().getStringForKey("error.period.format"));
                        return false;
                    }

                    config.setPeriod(intPeriod);
                }catch(NumberFormatException e){
                    LogEvent.logInfo(this.getClass().getName(), "method unkown", config.getName() + ": " +StringLocalization.instance().getStringForKey("error.period.format"));
                    return false;
                }
            }else{
                config.setPeriod(generalConfig.getPeriod());
            }

            if( config.getName() == null || config.getSourceDir() == null){
                LogEvent.logInfo(this.getClass().getName(), "method unkown", StringLocalization.instance().getStringForKey("error.missing.required"));
                return false;
            }
        }

        return true;
    }
}
