package com.frost.gasgo.userhub.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(schema = "userhub")
public class AddressData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long addressId;

    private String addressType;

    @Column(length = 100)
    private String addressLine1;

    @Column(length = 100)
    private String addressLine2;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String state;

    @Column(length = 100)
    private String country;

    @Column(length = 100)
    private Long pincode;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
        private UserData userData;

}
