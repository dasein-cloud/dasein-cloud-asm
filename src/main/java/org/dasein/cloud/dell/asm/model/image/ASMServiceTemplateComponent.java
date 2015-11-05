package org.dasein.cloud.dell.asm.model.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="components")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMServiceTemplateComponent {
    @XmlElement(name="id")
    private String id;
    @XmlElement(name="componentID")
    private String componentID;
    @XmlElement(name="componentValid")
    private ASMServiceTemplateComponentValid componentValid;
    @XmlElement(name="puppetCertName")
    private String puppetCertName;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="type")
    private String type;
    @XmlElement(name="teardown")
    private Boolean teardown;
    @XmlElement(name="asmGUID")
    private String asmGUID;
    @XmlElement(name="relatedComponents")
    private ASMServiceTemplateRelatedComponent relatedComponents;
    @XmlElement(name="resources")
    private List<ASMServiceTemplateComponentResource> resources;
    @XmlElement(name="manageFirmware")
    private boolean manageFirmware;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public ASMServiceTemplateComponentValid getComponentValid() {
        return componentValid;
    }

    public void setComponentValid(ASMServiceTemplateComponentValid componentValid) {
        this.componentValid = componentValid;
    }

    public String getPuppetCertName() { return puppetCertName; }

    public void setPuppetCertName(String puppetCertName) { this.puppetCertName = puppetCertName; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getTeardown() {
        return teardown;
    }

    public void setTeardown(Boolean teardown) {
        this.teardown = teardown;
    }

    public String getAsmGUID() { return asmGUID; }

    public void setAsmGUID(String asmGUID) { this.asmGUID = asmGUID; }

    public ASMServiceTemplateRelatedComponent getRelatedComponents() {
        return relatedComponents;
    }

    public void setRelatedComponents(ASMServiceTemplateRelatedComponent relatedComponents) {
        this.relatedComponents = relatedComponents;
    }

    public List<ASMServiceTemplateComponentResource> getResources() { return resources; }

    public void setResources(List<ASMServiceTemplateComponentResource> resources){ this.resources = resources; }

    public boolean isManageFirmware() {
        return manageFirmware;
    }

    public void setManageFirmware(boolean manageFirmware) {
        this.manageFirmware = manageFirmware;
    }
}
