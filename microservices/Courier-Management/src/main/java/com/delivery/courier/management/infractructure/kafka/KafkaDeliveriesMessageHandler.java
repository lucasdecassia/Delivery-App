package com.delivery.courier.management.infractructure.kafka;

import com.delivery.courier.management.infractructure.event.DeliveryFulFilledIntegrationEvent;
import com.delivery.courier.management.infractructure.event.DeliveryPlacedIntegrationEvent;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "deliveries.v1.events",
groupId = "courier-management")
@Slf4j
public class KafkaDeliveriesMessageHandler {

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Object object){
        log.info("default handler: {}", object);
    }

    @KafkaHandler
    public void  handle(@Payload DeliveryPlacedIntegrationEvent event){
        log.info("handle: {}", event);
    }

    @KafkaHandler
    public void handleFulFilled(@Payload DeliveryFulFilledIntegrationEvent event){
        log.info("handle: {}", event);
    }
}
