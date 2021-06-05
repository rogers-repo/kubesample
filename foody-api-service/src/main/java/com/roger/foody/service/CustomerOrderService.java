package com.roger.foody.service;

import com.roger.foody.domain.Order;
import com.roger.foody.exception.CustomerNotFoundException;
import com.roger.foody.exception.OrderExistsException;
import com.roger.foody.exception.OrderNotFoundException;

import java.util.List;

public interface CustomerOrderService {
    Order addNewOrder(Order order) throws OrderExistsException,CustomerNotFoundException;
    Order getOrderByOrderId(String orderId) throws OrderNotFoundException;
    Order getOrderByOrderIdAndCustomerId(String orderId,String customerID) throws OrderNotFoundException;
    List<Order> getAllOrderByCustomerId(String customerId,String per_page,String page) throws CustomerNotFoundException;
}
