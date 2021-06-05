package com.roger.deliveryagent.service;

import com.roger.deliveryagent.domain.Order;
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

    @Value(value = "${kafka.shoptopic}")
    private String shopTopic;


    public void sendMessageToCustomer(Order order) {
        customKafkaTemplate.send(customerTopic, order);
    }

    public void sendMessageToShop(Order order) {
        customKafkaTemplate.send(shopTopic, order);
    }

}
