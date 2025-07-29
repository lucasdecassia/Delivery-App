package com.delivery.delivery_tracking.domain.service;

import com.delivery.delivery_tracking.domain.exception.DomainException;
import com.delivery.delivery_tracking.domain.model.Delivery;
import com.delivery.delivery_tracking.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryCheckpointService {

    private final DeliveryRepository repo;

    public void place(UUID deliveryId){
        Delivery delivery = repo.findById(deliveryId)
                .orElseThrow(() -> new DomainException());
        delivery.place();
        repo.saveAndFlush(delivery);
    }

    public void pickup(UUID deliveryId, UUID courierId){
        Delivery delivery = repo.findById(deliveryId)
                .orElseThrow(() -> new DomainException());
        delivery.pickUp(courierId);
        repo.saveAndFlush(delivery);
    }

    public void complete(UUID deliveryId){
        Delivery delivery = repo.findById(deliveryId)
                .orElseThrow(() -> new DomainException());
        delivery.markAsDelivery();
        repo.saveAndFlush(delivery);
    }
}
