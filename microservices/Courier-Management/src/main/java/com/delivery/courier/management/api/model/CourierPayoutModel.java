package com.delivery.courier.management.api.model;

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
