package org.dasein.cloud.dell.asm.model.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMServiceTemplateRelatedEntry {
    @XmlElement(name="key")
    private String key;
    @XmlElement(name="value")
    private String value;

    public String getKey(){ return key; }

    public String getValue(){ return value; }

    public void setKey(String key){ this.key = key; }

    public void setValue(String value){ this.value = value; }
}
