package org.dasein.cloud.dell.asm.model.authentication;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="AuthenticateResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMAuthenticationResponseModel {

    @XmlElement(name="userName")
    private String userName;
    @XmlElement(name="domain")
    private String domain;
    @XmlElement(name="role")
    private String role;
    @XmlElement(name="apiKey")
    private String apiKey;
    @XmlElement(name="apiSecret")
    private String apiSecret;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }
}
