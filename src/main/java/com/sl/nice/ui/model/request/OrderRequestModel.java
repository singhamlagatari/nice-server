package com.sl.nice.ui.model.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderRequestModel {
    private String member_id;
    private String store_code;
    private String menu_id;
    private int menu_count;
    private Double menu_price;

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

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public int getMenu_count() {
        return menu_count;
    }

    public void setMenu_count(int menu_count) {
        this.menu_count = menu_count;
    }

    public Double getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(Double menu_price) {
        this.menu_price = menu_price;
    }
}
