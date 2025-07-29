package com.delivery.courier.management.domain.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CourierPayoutService {

    public BigDecimal calculate(Double distanceKM){
        return new BigDecimal(10)
                .multiply(new BigDecimal(distanceKM))
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
