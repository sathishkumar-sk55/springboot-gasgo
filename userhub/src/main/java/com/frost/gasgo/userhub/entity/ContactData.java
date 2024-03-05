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
public class ContactData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long contactId;

    private String contactType;

    @Column(length = 12)
    private String mobileNumber1;

    @Column(length = 12)
    private String mobileNumber2;

    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserData userData;
}
