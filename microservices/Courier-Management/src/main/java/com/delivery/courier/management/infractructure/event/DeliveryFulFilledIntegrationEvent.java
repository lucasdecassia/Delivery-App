package com.delivery.courier.management.infractructure.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class DeliveryFulFilledIntegrationEvent {
    private OffsetDateTime occorredAt;
    private UUID deliveryId;
}
