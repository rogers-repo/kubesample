package com.stackroute.deliveryagent.service;

import com.stackroute.deliveryagent.domain.Order;
import com.stackroute.deliveryagent.exception.OrderNotFound;

import java.util.List;

public interface AgentOrderService {
    List<Order> getAllDeliveryOrders(String agentId, String per_page, String page) throws OrderNotFound;
    Order updateOrderStatus(String orderId, String shopId,String status) throws OrderNotFound;
}
