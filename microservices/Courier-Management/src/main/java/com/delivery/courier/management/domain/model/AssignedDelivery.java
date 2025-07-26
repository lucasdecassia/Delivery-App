package com.delivery.courier.management.domain.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
@Getter
public class AssignedDelivery {

    @EqualsAndHashCode.Include
    private UUID id;

    private OffsetDateTime assignedAt;

    static AssignedDelivery pending(UUID deliveryId){
        AssignedDelivery delivery = new AssignedDelivery();
        delivery.setId(deliveryId);
        delivery.setAssignedAt(OffsetDateTime.now());
        return delivery;
    }


}
