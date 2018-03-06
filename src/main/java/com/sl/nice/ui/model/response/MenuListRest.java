package com.sl.nice.ui.model.response;

public class MenuListRest {

    private String result_code = "200";
    private String result_msg = "success";
    private MenuListValue result_data;

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

    public MenuListValue getResult_data() {
        return result_data;
    }

    public void setResult_data(MenuListValue result_data) {
        this.result_data = result_data;
    }
}
