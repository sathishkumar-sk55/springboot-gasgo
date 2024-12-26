package com.frost.gasgo.userhub.controller;

import com.frost.gasgo.commonlib.userhub.UserAuthRequest;
import com.frost.gasgo.commonlib.userhub.UserAuthResponse;
import com.frost.gasgo.userhub.customexpection.UserAlreadyExistException;
import com.frost.gasgo.userhub.customexpection.UserNotFoundException;
import com.frost.gasgo.userhub.entity.UserData;
import com.frost.gasgo.userhub.service.UserDataServiceImpl;
import com.frost.gasgo.userhub.wrapper.UserDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserDataController {

    @Autowired
    private UserDataServiceImpl userDataService;

    @PostMapping("adduser")
    public ResponseEntity<UserDataWrapper> addUser(@RequestBody UserData userData) throws UserAlreadyExistException {
        return userDataService.addUser(userData);
    }

    @GetMapping("getUser/{userId}")
    public ResponseEntity<UserDataWrapper> getUser(@PathVariable long userId) throws UserNotFoundException {
        return userDataService.getUserDataById(userId);
    }

    @PostMapping("verify/auth")
    public ResponseEntity<UserAuthResponse> passwordVerifivation(@RequestBody UserAuthRequest userAuth){
        return userDataService.passwordVerifivation(userAuth);
    }

}
