package org.dasein.cloud.dell.asm.model.virtualmachine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="Deployments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMDeploymentsModel {
    @XmlElement(name="Deployment")
    private List<ASMDeploymentModel> virtualMachines;

    public List<ASMDeploymentModel> getVirtualMachines() {
        return virtualMachines;
    }

    public void setImages(List<ASMDeploymentModel> virtualMachines) {
        this.virtualMachines = virtualMachines;
    }
}
