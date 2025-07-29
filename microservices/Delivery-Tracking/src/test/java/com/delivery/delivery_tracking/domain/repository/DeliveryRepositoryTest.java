package com.delivery.delivery_tracking.domain.repository;

import com.delivery.delivery_tracking.domain.model.ContactPoint;
import com.delivery.delivery_tracking.domain.model.Delivery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository repo;

    @Test
    public void shouldPersist(){
        Delivery delivery  = Delivery.draft();

        delivery.editPreparationDetails(createdValidPreparationDetails());

        delivery.addItem("Camputador", 2);
        delivery.addItem("Mouse", 2);

        repo.saveAndFlush(delivery);

        Delivery persistedDelivery = repo.findById(delivery.getId()).orElseThrow();

        assertEquals(2, persistedDelivery.getItems().size());

        delivery.place();
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