package org.dasein.cloud.dell.asm.model.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="relatedComponents")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMServiceTemplateRelatedComponent {
    @XmlElement(name="entry")
    private List<ASMServiceTemplateRelatedEntry> entries;

    public List<ASMServiceTemplateRelatedEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ASMServiceTemplateRelatedEntry> entries){ this.entries = entries; }
}
