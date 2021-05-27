package com.stackroute.deliveryagent.service;

import com.stackroute.deliveryagent.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerKafkaService {
    @KafkaListener(topics = "${kafka.shoptopic}", containerFactory = "customKafkaListenerContainerFactory")
    public void kafkaShopListener(Order order) {
        log.info("Recieved kafka message from shop : " + order);
    }
}
