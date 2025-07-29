package com.delivery.delivery_tracking.infrasctructure.http.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CourierPayoutModel {
    private BigDecimal payoutFee;
}
