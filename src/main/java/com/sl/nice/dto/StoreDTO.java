package com.sl.nice.dto;

import java.io.Serializable;

public class StoreDTO implements Serializable {

    private String id;
    private String company_id;
    private String store_code;
    private String store_name;
    private String store_img_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_img_url() {
        return store_img_url;
    }

    public void setStore_img_url(String store_img_url) {
        this.store_img_url = store_img_url;
    }
}
