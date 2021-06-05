package com.roger.deliveryagent.service;

import com.roger.deliveryagent.domain.Order;
import com.roger.deliveryagent.exception.OrderNotFound;
import com.roger.deliveryagent.repository.DeliveryAgentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentOrderServiceimpl implements AgentOrderService {
    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Order> getAllDeliveryOrders(String agentId, String per_page, String page) throws OrderNotFound {
        Page<Order> orders= deliveryAgentRepository.findByAgentAgentId(agentId, PageRequest.of(Integer.parseInt(page), Integer.parseInt(per_page)));
        if(!orders.hasContent())
        {
            throw new OrderNotFound();
        }
        return orders.getContent();
    }

    @Override
    public Order updateOrderStatus(String orderId, String shopId,String status) throws OrderNotFound {
            Order agentOrder=  deliveryAgentRepository.findOrderByOrderIdAndAgentAgentId(orderId, shopId).orElseThrow(OrderNotFound::new);
            Order order= modelMapper.map(agentOrder,Order.class);
            order.getAgent().setStatus(status);
            return deliveryAgentRepository.save(order);
    }



}
