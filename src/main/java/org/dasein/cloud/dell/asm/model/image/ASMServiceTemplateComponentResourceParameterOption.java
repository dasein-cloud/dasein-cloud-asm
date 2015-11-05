package org.dasein.cloud.dell.asm.model.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Drew on 10/16/2015.
 */
@XmlRootElement(name="options")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMServiceTemplateComponentResourceParameterOption {
    @XmlElement(name="value")
    private String value;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="dependencyTarget")
    private String dependencyTarget;
    @XmlElement(name="dependencyValue")
    private String dependencyValue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDependencyTarget() {
        return dependencyTarget;
    }

    public void setDependencyTarget(String dependencyTarget) {
        this.dependencyTarget = dependencyTarget;
    }

    public String getDependencyValue() {
        return dependencyValue;
    }

    public void setDependencyValue(String dependencyValue) {
        this.dependencyValue = dependencyValue;
    }
}
