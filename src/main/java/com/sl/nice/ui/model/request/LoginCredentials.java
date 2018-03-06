package com.sl.nice.ui.model.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginCredentials {
    private String member_id;
    private String password;
    private String device_id;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
