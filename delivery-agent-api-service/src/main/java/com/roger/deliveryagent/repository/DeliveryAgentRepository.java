package com.roger.deliveryagent.repository;

import com.roger.deliveryagent.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryAgentRepository extends MongoRepository<Order, String> {
    Page<Order> findByAgentAgentId(String agentId, Pageable pageable);
    Optional<Order> findOrderByOrderIdAndAgentAgentId(String orderId, String agentId);


    //Order getOrderByIdAndShopId(String orderId,String shopId) throws ShopNotExist;
    //Order updateOrderStatus (String orderId,String shopId,String status) throws OrderNotFoundException,ShopNotExist;

}

