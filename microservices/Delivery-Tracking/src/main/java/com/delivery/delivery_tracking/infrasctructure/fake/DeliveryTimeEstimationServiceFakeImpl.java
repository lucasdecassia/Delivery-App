package com.delivery.delivery_tracking.infrasctructure.fake;

import com.delivery.delivery_tracking.domain.model.ContactPoint;
import com.delivery.delivery_tracking.domain.service.DeliveryEstimate;
import com.delivery.delivery_tracking.domain.service.DeliveryTimeEstimationService;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class DeliveryTimeEstimationServiceFakeImpl implements DeliveryTimeEstimationService {

    @Override
    public DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver) {
        return new DeliveryEstimate(
                Duration.ofHours(3),
                3.1
        );
    }
}
