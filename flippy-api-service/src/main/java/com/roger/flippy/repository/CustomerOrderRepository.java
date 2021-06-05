package com.roger.flippy.repository;

import com.roger.flippy.domain.Customer;
import com.roger.flippy.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerOrderRepository extends MongoRepository<Order,String> {

   Optional<Order> findOrderByOrderIdAndCustomerCustomerId(String orderId, String customerId);
   //Optional<List<Order>> findByCustomerCustomerId(String customerId);
   Page<Order> findByCustomerCustomerId(String customerId, Pageable pageable);
     /* @Query("{'OrderId' : ?0, 'customer.customerid' : ?1}")
      Order findByOrderIdAndCustomerIdQuery(String orderId, String customerId);*/

}
