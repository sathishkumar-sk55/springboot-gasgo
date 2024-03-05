package com.frost.gasgo.commonlib.userhub;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthResponse {
    private Long userId;

    private String username;

    private String role;

    private Boolean isAuthentication;

    private String token;

    public UserAuthResponse(Boolean isAuthentication){
        this.isAuthentication = isAuthentication;
    }

}
