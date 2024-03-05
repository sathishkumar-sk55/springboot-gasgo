package com.frost.gasgo.userhub.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataWrapper {

    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
}
