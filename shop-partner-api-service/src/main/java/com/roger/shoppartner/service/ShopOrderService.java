package com.stackroute.shoppartner.service;

import com.stackroute.shoppartner.domain.Order;
import com.stackroute.shoppartner.exception.OrderNotFoundException;
import com.stackroute.shoppartner.exception.ShopNotExist;

import java.util.List;

public interface ShopOrderService {

    List<Order> getAllShopOrders(String shopId,String per_page,String page) throws ShopNotExist;
    Order getOrderByIdAndShopId(String orderId,String shopId) throws ShopNotExist;
    Order updateOrderStatus (String orderId,String shopId,String status) throws OrderNotFoundException,ShopNotExist;
}
