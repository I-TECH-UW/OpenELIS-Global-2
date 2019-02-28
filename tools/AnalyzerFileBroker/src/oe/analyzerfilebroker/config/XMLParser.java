package oe.analyzerfilebroker.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class XMLParser {
    public boolean parse(GeneralConfig generalConfig, List<AnalyzerConfig> analyzerConfigs){
        File file = generalConfig.getFile();

        if( !file.canRead()){
            file.setReadable(true);
        }

        SAXReader xmlReader = new SAXReader();
        Document configDoc;
        try {
            configDoc = xmlReader.read(file);
            Element root = configDoc.getRootElement();
            boolean valid = new GeneralParser().parse(generalConfig, root);
            if( valid ){
                valid = new AnalyzerParser().parse(generalConfig, analyzerConfigs, root );
            }
            return valid;
        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
