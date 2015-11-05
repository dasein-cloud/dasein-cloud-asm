package org.dasein.cloud.dell.asm.model.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="resources")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMServiceTemplateComponentResource {
    @XmlElement(name="id")
    private String id;
    @XmlElement(name="displayName")
    private String displayName;
    @XmlElement(name="parameters")
    private List<ASMServiceTemplateComponentResourceParameter> parameters;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<ASMServiceTemplateComponentResourceParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ASMServiceTemplateComponentResourceParameter> parameters) {
        this.parameters = parameters;
    }
}
