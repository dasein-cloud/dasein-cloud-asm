package org.dasein.cloud.dell.asm.model.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="templateValid")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMServiceTemplateValid {
    @XmlElement(name="valid")
    private boolean valid;

    public boolean getValid(){ return valid; }

    public void setValid(boolean valid){ this.valid = valid; }
}
