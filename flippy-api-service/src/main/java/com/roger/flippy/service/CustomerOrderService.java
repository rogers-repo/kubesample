package com.stackroute.flippy.service;

import com.stackroute.flippy.domain.Order;
import com.stackroute.flippy.exception.CustomerNotFoundException;
import com.stackroute.flippy.exception.OrderExistsException;
import com.stackroute.flippy.exception.OrderNotFoundException;

import java.util.List;

public interface CustomerOrderService {
    Order addNewOrder(Order order) throws OrderExistsException,CustomerNotFoundException;
    Order getOrderByOrderId(String orderId) throws OrderNotFoundException;
    Order getOrderByOrderIdAndCustomerId(String orderId,String customerID) throws OrderNotFoundException;
    List<Order> getAllOrderByCustomerId(String customerId,String per_page,String page) throws CustomerNotFoundException;
}
