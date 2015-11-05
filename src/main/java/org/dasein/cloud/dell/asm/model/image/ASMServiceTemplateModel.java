package org.dasein.cloud.dell.asm.model.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="ServiceTemplate")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMServiceTemplateModel{
    @XmlElement(name="id")
    private String id;
    @XmlElement(name="templateName")
    private String templateName;
    @XmlElement(name="templateDescription")
    private String templateDescription;
    @XmlElement(name="templateVersion")
    private String templateVersion;
    @XmlElement(name="templateValid")
    private ASMServiceTemplateValid templateValid;
    @XmlElement(name="templateLocked")
    private boolean templateLocked;
    @XmlElement(name="draft")
    private boolean draft;
    @XmlElement(name="createdDate")
    private String createdDate;
    @XmlElement(name="createdBy")
    private String createdBy;
    @XmlElement(name="updatedDate")
    private String updatedDate;
    @XmlElement(name="lastDeployedDate")
    private String lastDeployedDate;
    @XmlElement(name="updatedBy")
    private String updatedBy;
    @XmlElement(name="components")
    private List<ASMServiceTemplateComponent> components;
    @XmlElement(name="category")
    private String category;
    @XmlElement(name="enableApps")
    private boolean enableApps;
    @XmlElement(name="enableCluster")
    private boolean enableCluster;
    @XmlElement(name="enableServer")
    private boolean enableServer;
    @XmlElement(name="enableStorage")
    private boolean enableStorage;
    @XmlElement(name="enableVMs")
    private boolean enableVMs;
    @XmlElement(name="allUsersAllowed")
    private boolean allUsersAllowed;
    @XmlElement(name="manageFirmware")
    private boolean manageFirmware;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    public String getTemplateVersion() {
        return templateVersion;
    }

    public void setTemplateVersion(String templateVersion) {
        this.templateVersion = templateVersion;
    }

    public ASMServiceTemplateValid getTemplateValid() {
        return templateValid;
    }

    public void setTemplateValid(ASMServiceTemplateValid templateValid) {
        this.templateValid = templateValid;
    }

    public boolean isTemplateLocked() {
        return templateLocked;
    }

    public void setTemplateLocked(boolean templateLocked) {
        this.templateLocked = templateLocked;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getLastDeployedDate() {
        return lastDeployedDate;
    }

    public void setLastDeployedDate(String lastDeployedDate) {
        this.lastDeployedDate = lastDeployedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<ASMServiceTemplateComponent> getComponents() {
        return components;
    }

    public void setComponents(List<ASMServiceTemplateComponent> components) {
        this.components = components;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isEnableApps() {
        return enableApps;
    }

    public void setEnableApps(boolean enableApps) {
        this.enableApps = enableApps;
    }

    public boolean isEnableCluster() {
        return enableCluster;
    }

    public void setEnableCluster(boolean enableCluster) {
        this.enableCluster = enableCluster;
    }

    public boolean isEnableServer() {
        return enableServer;
    }

    public void setEnableServer(boolean enableServer) {
        this.enableServer = enableServer;
    }

    public boolean isEnableStorage() {
        return enableStorage;
    }

    public void setEnableStorage(boolean enableStorage) {
        this.enableStorage = enableStorage;
    }

    public boolean isEnableVMs() {
        return enableVMs;
    }

    public void setEnableVMs(boolean enableVMs) {
        this.enableVMs = enableVMs;
    }

    public boolean isAllUsersAllowed() {
        return allUsersAllowed;
    }

    public void setAllUsersAllowed(boolean allUsersAllowed) {
        this.allUsersAllowed = allUsersAllowed;
    }

    public boolean isManageFirmware() {
        return manageFirmware;
    }

    public void setManageFirmware(boolean manageFirmware) {
        this.manageFirmware = manageFirmware;
    }
}