package com.sl.nice.ui.model.request;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class OrderRequest {
    private List<OrderRequestModel> listItem;

    public List<OrderRequestModel> getListItem() {
        return listItem;
    }

    public void setListItem(List<OrderRequestModel> listItem) {
        this.listItem = listItem;
    }
}
