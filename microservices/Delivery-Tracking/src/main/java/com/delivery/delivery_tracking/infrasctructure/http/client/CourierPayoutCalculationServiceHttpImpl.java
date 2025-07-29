package com.delivery.delivery_tracking.infrasctructure.http.client;

import com.delivery.delivery_tracking.domain.service.CourierPayoutCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {

    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceKM) {
        var courierPayoutModel = courierAPIClient.payoutCalculation(
                new CourierPayoutCalculationInput(distanceKM));
        return courierPayoutModel.getPayoutFee();
    }
}
