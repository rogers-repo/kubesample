package com.stackroute.shoppartner.service;

import com.stackroute.shoppartner.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerKafkaService {

    @KafkaListener(topics = "${kafka.customertopic}", containerFactory = "customKafkaListenerContainerFactory")
    public void kafkaCustomerMessageListener(Order order) {
        log.info("Recieved kafka message from customer : " + order);
    }

    @KafkaListener(topics = "${kafka.deliverytopic}", containerFactory = "customKafkaListenerContainerFactory")
    public void kafkaDeliveryListener(Order order) {
        log.info("Recieved kafka message from delivery : " + order);
    }
}
