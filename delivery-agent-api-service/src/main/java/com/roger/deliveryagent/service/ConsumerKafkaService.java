package com.roger.deliveryagent.service;

import com.roger.deliveryagent.domain.Order;
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
