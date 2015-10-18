package org.dasein.cloud.dell.asm.model.authentication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="AuthenticateRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ASMAuthenticationRequestModel {

    @XmlElement(name="userName")
    private String userName;
    @XmlElement(name="domain")
    private String domain;
    @XmlElement(name="password")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
