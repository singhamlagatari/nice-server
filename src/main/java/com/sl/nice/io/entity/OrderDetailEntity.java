package com.sl.nice.io.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity( name="order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue
    private String id;
    private String menu_id;
    private String menu_count;
    private String menu_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_count() {
        return menu_count;
    }

    public void setMenu_count(String menu_count) {
        this.menu_count = menu_count;
    }

    public String getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(String menu_price) {
        this.menu_price = menu_price;
    }
}
