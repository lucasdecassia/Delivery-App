package com.delivery.delivery_tracking.domain.service;

import com.delivery.delivery_tracking.api.model.ContactPointInput;
import com.delivery.delivery_tracking.api.model.DeliveryInput;
import com.delivery.delivery_tracking.api.model.ItemInput;
import com.delivery.delivery_tracking.domain.exception.DomainException;
import com.delivery.delivery_tracking.domain.model.ContactPoint;
import com.delivery.delivery_tracking.domain.model.Delivery;
import com.delivery.delivery_tracking.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryPreparationService {

    private final DeliveryTimeEstimationService deliveryTimeEstimationService;
    private final CourierPayoutCalculationService courierPayoutCalculationService;
    private final DeliveryRepository deliveryRepository;

    @Transactional
    public Delivery draft(DeliveryInput input){
        Delivery delivery = Delivery.draft();
        handlePreparation(input, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }

    @Transactional
    public Delivery edit(UUID deliveryId, DeliveryInput input){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DomainException());
        delivery.removeItem(deliveryId);
        handlePreparation(input, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }

    private void handlePreparation(DeliveryInput input, Delivery delivery) {
        ContactPointInput senderInput = input.getSender();
        ContactPointInput recipientInput = input.getRecipient();

        ContactPoint sender = ContactPoint.builder()
                .street(senderInput.getStreet())
                .number(senderInput.getNumber())
                .complement(senderInput.getComplement())
                .name(senderInput.getName())
                .phone(senderInput.getPhone())
                .zipCode(senderInput.getZipCode())
                .build();


        ContactPoint recipient = ContactPoint.builder()
                .street(recipientInput.getStreet())
                .number(recipientInput.getNumber())
                .complement(recipientInput.getComplement())
                .name(recipientInput.getName())
                .phone(recipientInput.getPhone())
                .zipCode(recipientInput.getZipCode())
                .build();

        DeliveryEstimate estimate = deliveryTimeEstimationService.estimate(sender, recipient);
        BigDecimal calculatedPayout = courierPayoutCalculationService.calculatePayout(estimate.getDistanceKM());

        BigDecimal distanceFee = calculateFee(estimate.getDistanceKM());

        var preparationDetails = Delivery.PreparationDetails.builder()
                .recipient(recipient)
                .sender(sender)
                .expectedDeliveryTime(estimate.getEstimatedTime())
                .courierPayout(calculatedPayout)
                .distanceFee(distanceFee)
                .build();

        delivery.editPreparationDetails(preparationDetails);

        for(ItemInput item : input.getItems()){
            delivery.addItem(item.getName(), item.getQuantity());
        }
    }

    private BigDecimal calculateFee(Double distanceKM) {
        return new BigDecimal(3)
                .multiply(new BigDecimal(distanceKM))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

}
