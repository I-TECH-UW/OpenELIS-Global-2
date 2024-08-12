package org.openelisglobal.view;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tiles-definitions")
@XmlAccessorType(XmlAccessType.FIELD)
public class TilesDefinitions {

    @XmlElement(name = "definition")
    private List<Definition> definitions;

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinition(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public Definition getDefinitionByName(String name) {
        return definitions.stream().filter(x -> x.getName().equals(name)).findFirst().orElseThrow();
    }
}
