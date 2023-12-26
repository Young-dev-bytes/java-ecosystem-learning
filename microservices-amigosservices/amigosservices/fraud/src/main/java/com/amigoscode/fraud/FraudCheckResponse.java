package com.amigoscode.fraud;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FraudCheckResponse {
    private boolean isFraudulentCustomer;
    public FraudCheckResponse(boolean isFraudulentCustomer) {
    }
}
