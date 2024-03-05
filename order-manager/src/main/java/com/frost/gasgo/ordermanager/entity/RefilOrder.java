package com.frost.gasgo.ordermanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Date;


@Entity
@Data
@Table(schema = "orderdb")
public class RefilOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private String status;

    private String orderType;

    private long userId;

    private long addressId;

    private long contactId;

    private Date estimatedDeliveryDate;

}
