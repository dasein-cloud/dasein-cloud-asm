package org.dasein.cloud.dell.asm.model.virtualmachine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="deploymentDevice")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMDeploymentDeviceModel {
    @XmlElement(name="deviceType")
    private String deviceType;
    @XmlElement(name="ipAddress")
    private String ipAddress;
    @XmlElement(name="refId")
    private String referenceId;
    @XmlElement(name="serviceTag")
    private String serviceTag;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }
}
