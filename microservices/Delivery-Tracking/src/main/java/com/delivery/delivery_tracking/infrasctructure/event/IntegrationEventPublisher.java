package com.delivery.delivery_tracking.infrasctructure.event;

public interface IntegrationEventPublisher {
    void publish(Object event, String key, String topic);
}
