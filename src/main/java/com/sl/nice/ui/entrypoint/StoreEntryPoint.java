package com.sl.nice.ui.entrypoint;

import com.sl.nice.dto.MenuDTO;
import com.sl.nice.dto.OrderDetail;
import com.sl.nice.dto.StoreDTO;
import com.sl.nice.service.MenuService;
import com.sl.nice.service.StoreService;
import com.sl.nice.service.impl.MenuServiceImpl;
import com.sl.nice.service.impl.StoreServiceImpl;
import com.sl.nice.ui.model.request.MenuListRequest;
import com.sl.nice.ui.model.request.OrderRequestModel;
import com.sl.nice.ui.model.request.StoreListRequest;
import com.sl.nice.ui.model.response.MenuListRest;
import com.sl.nice.ui.model.response.MenuListValue;
import com.sl.nice.ui.model.response.StoreListRest;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("store")
public class StoreEntryPoint {

    @POST
    @Path("/tree/list/root")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StoreListRest storeList(StoreListRequest storeListRequest){

        int start = storeListRequest.getOffset();
        int limit = storeListRequest.getCount();

        StoreService storeService = new StoreServiceImpl();
        List<StoreDTO> stores = storeService.getStores(start, limit);

        StoreListRest storeListRest = new StoreListRest();
        storeListRest.setResult_data(stores);

        return storeListRest;
    }

    @POST
    @Path("/menu/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MenuListRest menuList(MenuListRequest menuListRequest) {

        String memberId = menuListRequest.getMember_id();
        String storeCode = menuListRequest.getStore_code();
        String store = menuListRequest.getStore_yn();

        System.out.println("storeCode " + storeCode);

        MenuService menuService = new MenuServiceImpl();
        List<MenuDTO> menu = menuService.getMenu(storeCode);
        MenuListRest menuListRest = new MenuListRest();

        MenuListValue resultData = new MenuListValue();
        resultData.setMenu_list(menu);

        menuListRest.setResult_data(resultData);

        return menuListRest;
    }

    @POST
    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDetail> orderMenu(List<OrderRequestModel> orderRequestsModel) {

        List<OrderDetail> orderModel = new ArrayList<OrderDetail>();
        Double priceOrder = 0.0;

        for (OrderRequestModel orderRequestModel : orderRequestsModel) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(orderRequestModel, orderDetail);
            Double totalPrice = orderDetail.getMenu_price() * orderDetail.getMenu_count();
            orderDetail.setTotal_price(totalPrice);
            priceOrder = priceOrder + totalPrice;
            orderModel.add(orderDetail);
        }

        return orderModel;
    }

}
