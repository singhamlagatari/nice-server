package com.sl.nice.ui.model.response;

import com.sl.nice.dto.UserDTO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthenticationDetails {
    private String result_code = "200";
    private String result_msg = "success";
    private UserProfileRest result_data;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    public UserProfileRest getResult_data() {
        return result_data;
    }

    public void setResult_data(UserProfileRest result_data) {
        this.result_data = result_data;
    }
}






