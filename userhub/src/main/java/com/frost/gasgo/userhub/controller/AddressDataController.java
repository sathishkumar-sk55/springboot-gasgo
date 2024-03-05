package com.frost.gasgo.userhub.controller;

import com.frost.gasgo.userhub.customexpection.AddressNotFoundException;
import com.frost.gasgo.userhub.customexpection.UserNotFoundException;
import com.frost.gasgo.userhub.entity.AddressData;
import com.frost.gasgo.userhub.service.AddressDataServiceImpl;
import com.frost.gasgo.userhub.wrapper.AddressDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressDataController {


    @Autowired
    private AddressDataServiceImpl addressDataService;

    @PostMapping("addAdress/{userId}")
    public ResponseEntity<AddressDataWrapper> addAddress(@PathVariable long userId, @RequestBody AddressData addressData) throws UserNotFoundException {
        return addressDataService.addAddress(userId, addressData);
    }

    @GetMapping("getAddressByAddressId/{addressId}")
    public ResponseEntity<AddressDataWrapper> getAddressbyAddressId(@PathVariable long addressId) throws AddressNotFoundException {
        return addressDataService.getAddressbyAddressId(addressId);
    }

    @GetMapping("getAllAddressByUserId/{userId}")
    public ResponseEntity<List<AddressDataWrapper>> getAllAddressbyUserId(@PathVariable long userId) throws UserNotFoundException {
        return addressDataService.getAllAddressbyUserId(userId);
    }

    @DeleteMapping("deleteAddressByAddressId/{addressId}")
    public ResponseEntity deleteAddressByAddressId(@PathVariable long addressId) throws AddressNotFoundException {

        return addressDataService.deleteAddressByAddressId(addressId);
    }

    @PatchMapping("updateAddressById/{addressId}")
    public ResponseEntity updateAddressById(@PathVariable long addressId, @RequestBody AddressDataWrapper addressDataWrapper) throws AddressNotFoundException {
        return addressDataService.updateAddressById(addressId, addressDataWrapper);
    }
}
