package org.dasein.cloud.dell.asm.model.image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="parameters")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMServiceTemplateComponentResourceParameter {
    @XmlElement(name="id")
    private String id;
    @XmlElement(name="value")
    private String value;
    @XmlElement(name="type")
    private String type;
    @XmlElement(name="displayName")
    private String displayName;
    @XmlElement(name="required")
    private Boolean required;
    @XmlElement(name="requiredAtDeployment")
    private Boolean requiredAtDeployment;
    @XmlElement(name="hideFromTemplate")
    private Boolean hideFromTemplate;
    @XmlElement(name="min")
    private int min;
    @XmlElement(name="max")
    private int max;
    @XmlElement(name="dependencyTarget")
    private String dependencyTarget;
    @XmlElement(name="dependencyValue")
    private String dependencyValue;
    @XmlElement(name="options")
    private List<ASMServiceTemplateComponentResourceParameterOption> options;
    @XmlElement(name="readOnly")
    private Boolean readOnly;
    @XmlElement(name="generated")
    private Boolean generated;
    @XmlElement(name="infoIcon")
    private Boolean infoIcon;
    @XmlElement(name="maxLength")
    private int maxLength;
    @XmlElement(name="step")
    private int step;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getRequiredAtDeployment() {
        return requiredAtDeployment;
    }

    public void setRequiredAtDeployment(Boolean requiredAtDeployment) {
        this.requiredAtDeployment = requiredAtDeployment;
    }

    public Boolean getHideFromTemplate() {
        return hideFromTemplate;
    }

    public void setHideFromTemplate(Boolean hideFromTemplate) {
        this.hideFromTemplate = hideFromTemplate;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getDependencyTarget() { return dependencyTarget; }

    public void setDependencyTarget(String dependencyTarget) { this.dependencyTarget = dependencyTarget; }

    public String getDependencyValue() { return dependencyValue; }

    public void setDependencyValue(String dependencyValue) { this.dependencyValue = dependencyValue; }

    public List<ASMServiceTemplateComponentResourceParameterOption> getOptions() {
        return options;
    }

    public void setOptions(List<ASMServiceTemplateComponentResourceParameterOption> options) {
        this.options = options;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Boolean getGenerated() {
        return generated;
    }

    public void setGenerated(Boolean generated) {
        this.generated = generated;
    }

    public Boolean getInfoIcon() {
        return infoIcon;
    }

    public void setInfoIcon(Boolean infoIcon) {
        this.infoIcon = infoIcon;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
