package com.delivery.delivery_tracking.infrasctructure.http.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CourierAPIClientConfig {

    @Bean
    public CourierAPIClient courierAPIClient(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder
                .baseUrl("http://localhost:8081")
                .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(adapter)
                .build();

        return factory.createClient(CourierAPIClient.class);
    }
}
