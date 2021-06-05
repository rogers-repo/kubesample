package com.roger.flippy.service;

import com.roger.flippy.domain.Order;
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

    @Value(value = "${kafka.shoptopic}")
    private String topicName;


    public void sendOrders(Order order) {
        customKafkaTemplate.send(topicName, order);
    }

}
