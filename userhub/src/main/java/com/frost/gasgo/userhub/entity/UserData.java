package com.frost.gasgo.userhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(schema = "userhub")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(length = 30)
    private String firstName;

    @Column(length = 30)
    private String lastName;

    @OneToMany(mappedBy = "userData")
    private List<AddressData> addressData;

    @OneToMany(mappedBy = "userData")
    private List<ContactData> contactData;

}
