package com.stackroute.shoppartner.service;

import com.stackroute.shoppartner.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Order> customKafkaTemplate;

    @Value(value = "${kafka.customertopic}")
    private String customerTopic;

    @Value(value = "${kafka.deliverytopic}")
    private String deliveryTopic;


    public void sendMessageToCustomer(Order order) {
        customKafkaTemplate.send(customerTopic, order);
    }

    public void sendMessageTodelivery(Order order) {
        customKafkaTemplate.send(deliveryTopic, order);
    }

}
