package com.in28minutes.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(
                                f -> f.addRequestHeader("MyHeader", "MyURI")
                                        .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://CURRENCY-EXCHANGE-SERVICE"/*"lb://currency-exchange-service"*/))
                .route(p -> p.path("/currency-converter/**")
                        .uri("lb://CURRENCY-CONVERSION-SERVICE"))
                .route(p -> p.path("/currency-converter-feign/**")
                        .uri("lb://CURRENCY-CONVERSION-SERVICE"))
                .route(p -> p.path("/currency-converter-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-converter-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://CURRENCY-CONVERSION-SERVICE"))
                .build();
    }
}
