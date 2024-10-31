package org.openelisglobal.view;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "definition")
@XmlAccessorType(XmlAccessType.FIELD)
public class Definition {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "extends")
    private String extension;

    @XmlAttribute(name = "template")
    private String template;

    @XmlElement(name = "put-attribute")
    private List<PutAttribute> putAttributes;

    public Definition() {
    }

    public Definition(String name, String extension, String template, List<PutAttribute> putAttributes) {
        this.name = name;
        this.extension = extension;
        this.template = template;
        this.putAttributes = putAttributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtends() {
        return extension;
    }

    public void setExtends(String extension) {
        this.extension = extension;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<PutAttribute> getPutAttributes() {
        return putAttributes;
    }

    public void setPutAttributes(List<PutAttribute> putAttributes) {
        this.putAttributes = putAttributes;
    }
}
