package com.sl.nice.service;

import com.sl.nice.dto.OrderDetail;
import com.sl.nice.dto.StoreDTO;

import java.util.List;

public interface StoreService {
    List<StoreDTO> getStores(int start, int limit);
    List<OrderDetail> saveOrder(List<OrderDetail> orderDetails);
}
