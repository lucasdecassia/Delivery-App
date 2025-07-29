package com.delivery.delivery_tracking.infrasctructure.event;

import com.delivery.delivery_tracking.domain.event.DeliveryFulFIlledEvent;
import com.delivery.delivery_tracking.domain.event.DeliveryPickedUpEvent;
import com.delivery.delivery_tracking.domain.event.DeliveryPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.delivery.delivery_tracking.infrasctructure.kafka.KafkaTopicConfig.deliveryEventsTopicName;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryDomainEventHandle {

    private final IntegrationEventPublisher integrationEventPublisher;

    @EventListener
    public void handle(DeliveryPlacedEvent event){
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(),
                deliveryEventsTopicName);
    }

    @EventListener
    public void handle(DeliveryPickedUpEvent event){
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(),
                deliveryEventsTopicName);
    }

    @EventListener
    public void handle(DeliveryFulFIlledEvent event){
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(),
                deliveryEventsTopicName);
    }
}
