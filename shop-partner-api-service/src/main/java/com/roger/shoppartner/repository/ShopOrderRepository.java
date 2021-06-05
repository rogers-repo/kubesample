package com.roger.shoppartner.repository;

import com.roger.shoppartner.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopOrderRepository extends MongoRepository<Order,String> {
    Page<Order> findByShopPartnerShopPartnerid(String shopId, Pageable pageable);
    Optional<Order> findOrderByOrderIdAndShopPartnerShopPartnerid(String orderId,String shopId);


}


