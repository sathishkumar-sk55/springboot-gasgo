package com.frost.gasgo.userhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.frost.gasgo.userhub.entity")
public class UserhubApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserhubApplication.class, args);
    }

}
