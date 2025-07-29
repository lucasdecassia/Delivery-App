package com.delivery.courier.management.domain.service;

import com.delivery.courier.management.domain.model.Courier;
import com.delivery.courier.management.domain.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourierDeliveryService {

    private final CourierRepository repository;

    public void assined(UUID deliveryId){
        Courier courier = repository.findTop1ByOrderByLastFulfilledDeliveryAtAsc()
                .orElseThrow();

        courier.assign(deliveryId);
        repository.saveAndFlush(courier);

        log.info("courier {} assigned to delivery {}", courier.getId(), deliveryId);
    }

    public void fulfilled(UUID deliveryId){
        Courier courier = repository.findByPendingDeliveries_id(deliveryId)
                .orElseThrow();

        courier.fulfill(deliveryId);
        repository.saveAndFlush(courier);

        log.info("courier {} fulfilled delivery {}", courier.getId(), deliveryId);
    }
}
