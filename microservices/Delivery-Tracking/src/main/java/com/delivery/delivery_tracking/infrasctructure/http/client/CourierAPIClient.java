package com.delivery.delivery_tracking.infrasctructure.http.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/v1/couries")
public interface CourierAPIClient {

    @PostExchange("/payout-calculation")
    CourierPayoutModel calculatePayout(@RequestBody CourierPayoutCalculateInput input);

}
