package com.delivery.delivery_tracking.domain.service;

import java.math.BigDecimal;

public interface CourierPayoutCalculationService {
    BigDecimal calculatePayout(Double distanceKM);
}
