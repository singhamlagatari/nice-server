package com.sl.nice.dto;

import java.io.Serializable;

public class GroupDTO implements Serializable {

    private String store_id;
    private String store_code;
    private String menu_name;
    private Double menu_price;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public Double getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(Double menu_price) {
        this.menu_price = menu_price;
    }
}
