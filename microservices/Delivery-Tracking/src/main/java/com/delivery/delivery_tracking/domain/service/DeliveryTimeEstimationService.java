package com.delivery.delivery_tracking.domain.service;

import com.delivery.delivery_tracking.domain.model.ContactPoint;

public interface DeliveryTimeEstimationService {
    DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver);
}
