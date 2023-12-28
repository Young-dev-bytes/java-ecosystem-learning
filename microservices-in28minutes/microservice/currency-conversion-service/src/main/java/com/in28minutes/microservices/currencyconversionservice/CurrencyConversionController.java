package com.in28minutes.microservices.currencyconversionservice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private final CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
    private final RestTemplate restTemplate;

    public CurrencyConversionController(CurrencyExchangeServiceProxy currencyExchangeServiceProxy, RestTemplate restTemplate) {
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity(
                // "http://localhost:8080/currency-exchange/from/{from}/to/{to}"
                "http://CURRENCY-EXCHANGE-SERVICE/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class,
                uriVariables);

        CurrencyConversionBean response = responseEntity.getBody();
        return new CurrencyConversionBean(
                response.getId(),
                response.getFrom(),
                response.getTo(),
                response.getConversionMultiple(),
                quantity,
                quantity.multiply(response.getConversionMultiple()),
                "restTemplate - " + response.getEnvironment());
    }


    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                       @PathVariable String to,
                                                       @PathVariable BigDecimal quantity) {

        CurrencyConversionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
        return new CurrencyConversionBean(response.getId(),
                response.getFrom(),
                response.getTo(),
                response.getConversionMultiple(),
                quantity,
                quantity.multiply(response.getConversionMultiple()),
                "feign - " + response.getEnvironment());
    }
}
