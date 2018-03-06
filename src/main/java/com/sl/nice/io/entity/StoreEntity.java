package com.sl.nice.io.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity( name="store_master")
public class StoreEntity {

    @Id
    private String id;
    private String company_id;
    private String store_code;
    private String store_name;
    private String store_image_url;

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

    public String getStore_image_url() {
        return store_image_url;
    }

    public void setStore_image_url(String store_image_url) {
        this.store_image_url = store_image_url;
    }
}
