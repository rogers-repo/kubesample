package com.roger.shoppartner.service;

import com.roger.shoppartner.domain.Order;
import com.roger.shoppartner.exception.OrderNotFoundException;
import com.roger.shoppartner.exception.ShopNotExist;

import java.util.List;

public interface ShopOrderService {

    List<Order> getAllShopOrders(String shopId,String per_page,String page) throws ShopNotExist;
    Order getOrderByIdAndShopId(String orderId,String shopId) throws ShopNotExist;
    Order updateOrderStatus (String orderId,String shopId,String status) throws OrderNotFoundException,ShopNotExist;
}
