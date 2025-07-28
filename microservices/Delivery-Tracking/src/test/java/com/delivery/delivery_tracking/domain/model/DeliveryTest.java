package com.delivery.delivery_tracking.domain.model;

import com.delivery.delivery_tracking.domain.model.exception.DomainException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeliveryTest {

    @Test
    public void shoulChangeToPlaced(){
        Delivery delivery  = Delivery.draft();

        delivery.editPreparationDetails(createdValidPreparationDetails());

        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());
    }

    @Test
    public void shouldNotPlaced(){
        Delivery delivery  = Delivery.draft();
        assertThrows(DomainException.class, () -> {
            delivery.place();
        });

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlacedAt());

    }

    public Delivery.PreparationDetails createdValidPreparationDetails(){
        ContactPoint sender =ContactPoint.builder()
                .zipCode("00000-00")
                .street("Rua Brasilia")
                .number("120")
                .complement("AP 507")
                .name("Lucas")
                .phone("(61) 99902-2089")
                .build();

        ContactPoint recipient =ContactPoint.builder()
                .zipCode("34340-00")
                .street("Rua SP")
                .number("45")
                .complement("")
                .name("Maria")
                .phone("(61) 35455-5455")
                .build();


        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal(  "15.00"))
                .courierPayout(new BigDecimal("10.00"))
                .expectedDeliveryTime(Duration.ofHours(4))
                .build();
    }
}