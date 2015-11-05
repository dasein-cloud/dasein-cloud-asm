package org.dasein.cloud.dell.asm.model.virtualmachine;

import org.dasein.cloud.dell.asm.model.image.ASMServiceTemplateModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Deployment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMDeploymentModel {
    @XmlElement(name="createdBy")
    private String createdBy;
    @XmlElement(name="createdDate")
    private String createdDate;
    @XmlElement(name="deploymentDescription")
    private String description;
    @XmlElement(name="deploymentDevice")
    private ASMDeploymentDeviceModel device;
    @XmlElement(name="deploymentName")
    private String name;
    @XmlElement(name="id")
    private String providerVirtualMachineId;
    @XmlElement(name="numberOfDeployments")
    private int numberOfDeployments;
    @XmlElement(name="serviceTemplate")
    private ASMServiceTemplateModel image;
    @XmlElement(name="status")
    private String status;
    @XmlElement(name="teardown")
    private Boolean teardown;
    @XmlElement(name="templateValid")
    private Boolean templateValid;
    @XmlElement(name="updatedDate")
    private String updatedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ASMDeploymentDeviceModel getDevice() {
        return device;
    }

    public void setDevice(ASMDeploymentDeviceModel device) {
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProviderVirtualMachineId() {
        return providerVirtualMachineId;
    }

    public void setProviderVirtualMachineId(String providerVirtualMachineId) {
        this.providerVirtualMachineId = providerVirtualMachineId;
    }

    public int getNumberOfDeployments(){ return numberOfDeployments; }

    public void setNumberOfDeployments(int numberOfDeployments){ this.numberOfDeployments = numberOfDeployments; }

    public ASMServiceTemplateModel getImage() {
        return image;
    }

    public void setImage(ASMServiceTemplateModel image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getTeardown() { return teardown; }

    public void setTeardown(Boolean teardown) { this.teardown = teardown; }

    public boolean isTemplateValid() {
        return templateValid;
    }

    public void setTemplateValid(boolean templateValid) {
        this.templateValid = templateValid;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
