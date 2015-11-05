package org.dasein.cloud.dell.asm.model.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="ServiceTemplates")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMServiceTemplatesModel {
    @XmlElement(name="ServiceTemplate")
    private List<ASMServiceTemplateModel> images;

    public List<ASMServiceTemplateModel> getImages() {
        return images;
    }

    public void setImages(List<ASMServiceTemplateModel> images) {
        this.images = images;
    }
}
