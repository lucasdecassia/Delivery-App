package com.delivery.delivery_tracking.infrasctructure.event;

import com.delivery.delivery_tracking.domain.event.DeliveryFulFIlledEvent;
import com.delivery.delivery_tracking.domain.event.DeliveryPickedUpEvent;
import com.delivery.delivery_tracking.domain.event.DeliveryPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryDomainEventHandle {

    @EventListener
    public void handle(DeliveryPlacedEvent event){
        log.info(event.toString());
    }

    @EventListener
    public void handle(DeliveryPickedUpEvent event){
        log.info(event.toString());
    }

    @EventListener
    public void handle(DeliveryFulFIlledEvent event){
        log.info(event.toString());
    }
}
