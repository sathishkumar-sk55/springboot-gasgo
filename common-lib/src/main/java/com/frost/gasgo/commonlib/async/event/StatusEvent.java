package com.frost.gasgo.commonlib.async.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusEvent {
    private long orderId;
    private String status;
    private String estimatedDeliveryDate;

    public StatusEvent(String messageReceived) {
    }
}
