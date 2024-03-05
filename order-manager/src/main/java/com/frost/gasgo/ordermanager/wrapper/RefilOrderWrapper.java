package com.frost.gasgo.ordermanager.wrapper;

import com.frost.gasgo.ordermanager.externalclass.AddressDataWrapper;
import com.frost.gasgo.ordermanager.externalclass.ContactDataWrapper;
import com.frost.gasgo.ordermanager.externalclass.UserDataWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefilOrderWrapper {
    private long orderId;

    private String status;

    private String orderType;

    private UserDataWrapper userData;

    private AddressDataWrapper addressData;

    private ContactDataWrapper contactData;
}
