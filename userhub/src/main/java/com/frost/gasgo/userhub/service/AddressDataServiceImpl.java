package com.frost.gasgo.userhub.service;

import com.frost.gasgo.userhub.customexpection.AddressNotFoundException;
import com.frost.gasgo.userhub.customexpection.UserNotFoundException;
import com.frost.gasgo.userhub.entity.AddressData;
import com.frost.gasgo.userhub.entity.UserData;
import com.frost.gasgo.userhub.repository.AddressDataRepository;
import com.frost.gasgo.userhub.repository.UserDataRepository;
import com.frost.gasgo.userhub.wrapper.AddressDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressDataServiceImpl {

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    AddressDataRepository addressDataRepository;

    public ResponseEntity<AddressDataWrapper> addAddress(long userId, AddressData addressData) throws UserNotFoundException {

        UserData userData = userDataRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found address cant be added"));

        addressData.setUserData(userData);
        AddressData savedAddressData = addressDataRepository.save(addressData);

        AddressDataWrapper addressDataWrapper =
                AddressDataWrapper.builder()
                        .addressId(savedAddressData.getAddressId())
                        .addressType(savedAddressData.getAddressType())
                        .addressLine1(savedAddressData.getAddressLine1())
                        .addressLine2(savedAddressData.getAddressLine2())
                        .city(savedAddressData.getCity())
                        .state(savedAddressData.getState())
                        .country(savedAddressData.getCountry())
                        .pincode(savedAddressData.getPincode())
                        .build();


        return ResponseEntity.status(HttpStatus.CREATED).body(addressDataWrapper);
    }

    public ResponseEntity<AddressDataWrapper> getAddressbyAddressId(long addressId) throws AddressNotFoundException {
        AddressData fetechedAddressData = addressDataRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address with addressId : " + addressId + " Not Found"));

        AddressDataWrapper addressDataWrapper =
                AddressDataWrapper.builder()
                        .addressId(fetechedAddressData.getAddressId())
                        .addressType(fetechedAddressData.getAddressType())
                        .addressLine1(fetechedAddressData.getAddressLine1())
                        .addressLine2(fetechedAddressData.getAddressLine2())
                        .city(fetechedAddressData.getCity())
                        .state(fetechedAddressData.getState())
                        .country(fetechedAddressData.getCountry())
                        .pincode(fetechedAddressData.getPincode())
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(addressDataWrapper);
    }

    public ResponseEntity<List<AddressDataWrapper>> getAllAddressbyUserId(long userId) throws UserNotFoundException {
        userDataRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with user id : " + userId + " not found"));

        List<AddressData> fetechedAddressDataList = addressDataRepository.findAllByUserId(userId);

        List<AddressDataWrapper> fetechedAddressDataWrapper = new ArrayList<>();

        for (AddressData addressData : fetechedAddressDataList) {
            AddressDataWrapper addressDataWrapper =
                    AddressDataWrapper.builder()
                            .addressId(addressData.getAddressId())
                            .addressType(addressData.getAddressType())
                            .addressLine1(addressData.getAddressLine1())
                            .addressLine2(addressData.getAddressLine2())
                            .city(addressData.getCity())
                            .state(addressData.getState())
                            .country(addressData.getCountry())
                            .pincode(addressData.getPincode())
                            .build();
            fetechedAddressDataWrapper.add(addressDataWrapper);
        }


        return ResponseEntity.status(HttpStatus.OK).body(fetechedAddressDataWrapper);
    }

    public ResponseEntity<String> deleteAddressByAddressId(long addressId) throws AddressNotFoundException {

        addressDataRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address with AddressID : " + addressId + " Not Found"));
        addressDataRepository.deleteById(addressId);

        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    public ResponseEntity<AddressDataWrapper> updateAddressById(long addressId, AddressDataWrapper addressDataWrapper) throws AddressNotFoundException {

        AddressData fetchedAddressData = addressDataRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address with AddressID : " + addressId + " Not Found"));

        if (addressDataWrapper.getAddressLine1() != null) {
            fetchedAddressData.setAddressLine1(addressDataWrapper.getAddressLine1());
        }

        if (addressDataWrapper.getAddressLine2() != null) {
            fetchedAddressData.setAddressLine2(addressDataWrapper.getAddressLine2());
        }

        if (addressDataWrapper.getCity() != null) {
            fetchedAddressData.setCity(addressDataWrapper.getCity());
        }

        if (addressDataWrapper.getState() != null) {
            fetchedAddressData.setState(addressDataWrapper.getState());
        }

        if (addressDataWrapper.getCountry() != null) {
            fetchedAddressData.setCountry(addressDataWrapper.getCountry());
        }

        if (addressDataWrapper.getPincode() != null) {
            fetchedAddressData.setPincode(addressDataWrapper.getPincode());
        }

        addressDataRepository.save(fetchedAddressData);

        addressDataWrapper.setAddressId(addressId);

        return ResponseEntity.status(HttpStatus.OK).body(addressDataWrapper);

    }
}
