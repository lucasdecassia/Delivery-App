package com.delivery.delivery_tracking.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryStatusTest {

    @Test
    void draf_canChangeToWaitingForCourier(){
        assertTrue(
                DeliveryStatus.DRAFT.canChangeTo(DeliveryStatus.WAITING_FOR_COURIER)
        );
    }

    @Test
    void draf_canChangeToInTransit(){
        assertTrue(
                DeliveryStatus.DRAFT.canNotChangeTo(DeliveryStatus.IN_TRANSIT)
        );
    }

}