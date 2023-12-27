package com.amigoscode.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {

    private Integer toCustomerId;
    private String toCustomerName;
    private String message;
    private String email;
}
