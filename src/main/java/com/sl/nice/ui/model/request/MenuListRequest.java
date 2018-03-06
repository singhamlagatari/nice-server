package com.sl.nice.ui.model.request;

public class MenuListRequest {

    private String member_id;
    private String store_code;
    private String store_yn;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public String getStore_yn() {
        return store_yn;
    }

    public void setStore_yn(String store_yn) {
        this.store_yn = store_yn;
    }
}
