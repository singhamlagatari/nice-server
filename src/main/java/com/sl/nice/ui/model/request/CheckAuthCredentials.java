package com.sl.nice.ui.model.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CheckAuthCredentials {
    private String auth_token;

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }
}
