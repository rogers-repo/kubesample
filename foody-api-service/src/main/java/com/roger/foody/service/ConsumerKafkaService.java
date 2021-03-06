package com.roger.foody.service;

import com.roger.foody.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerKafkaService {

    @KafkaListener(topics = "${kafka.shoptopic}", containerFactory = "customKafkaListenerContainerFactory")
    public void kafkaShopMessageListener(Order order) {
        log.info("Recieved kafka message from shop : " + order);
    }

    @KafkaListener(topics = "${kafka.deliverytopic}", containerFactory = "customKafkaListenerContainerFactory")
    public void kafkaDeliveryListener(Order order) {
        log.info("Recieved kafka message from delivery : " + order);
    }
}
