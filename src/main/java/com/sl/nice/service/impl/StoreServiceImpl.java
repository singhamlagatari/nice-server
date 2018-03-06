package com.sl.nice.service.impl;

import com.sl.nice.dto.OrderDetail;
import com.sl.nice.dto.StoreDTO;
import com.sl.nice.io.dao.DAO;
import com.sl.nice.io.dao.impl.MySQLDAO;
import com.sl.nice.service.StoreService;
import com.sl.nice.utils.UserProfileUtils;

import java.util.List;

public class StoreServiceImpl implements StoreService {

    UserProfileUtils userProfileUtils = new UserProfileUtils();
    DAO database;

    public StoreServiceImpl() {
        this.database = new MySQLDAO();
    }

    @Override
    public List<StoreDTO> getStores(int start, int limit) {

        List<StoreDTO> stores = null;

        // Get users from database
        try {
            this.database.openConnection();
            stores = this.database.getStores(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return stores;
    }

    @Override
    public List<OrderDetail> saveOrder(List<OrderDetail> orderDetails) {


        return  orderDetails;
    };

}

