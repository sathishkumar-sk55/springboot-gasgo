package com.frost.gasgo.userhub.service;


import com.frost.gasgo.userhub.customexpection.AddressNotFoundException;
import com.frost.gasgo.userhub.customexpection.ContactNotFoundException;
import com.frost.gasgo.userhub.customexpection.UserNotFoundException;
import com.frost.gasgo.userhub.entity.AddressData;
import com.frost.gasgo.userhub.entity.ContactData;
import com.frost.gasgo.userhub.entity.UserData;
import com.frost.gasgo.userhub.repository.ContactDataRepository;
import com.frost.gasgo.userhub.repository.UserDataRepository;
import com.frost.gasgo.userhub.wrapper.AddressDataWrapper;
import com.frost.gasgo.userhub.wrapper.ContactDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactDataServiceImpl {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private ContactDataRepository contactDataRepository;


    public ResponseEntity<ContactDataWrapper> addContact(long userId, ContactData contactData) throws UserNotFoundException {
        UserData userData = userDataRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found address cant be added"));

        contactData.setUserData(userData);
        ContactData savedContactData = contactDataRepository.save(contactData);

        ContactDataWrapper contactDataWrapper =
                ContactDataWrapper
                        .builder()
                        .contactId(savedContactData.getContactId())
                        .contactType(savedContactData.getContactType())
                        .mobileNumber1(savedContactData.getMobileNumber1())
                        .mobileNumber2(savedContactData.getMobileNumber2())
                        .email(savedContactData.getEmail())
                        .build();


        return ResponseEntity.status(HttpStatus.CREATED).body(contactDataWrapper);
    }

    public ResponseEntity<ContactDataWrapper> getConatactByContactId(long contactId) throws ContactNotFoundException {
        ContactData fetechedContactData = contactDataRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException("Contact with ContactId : " + contactId + " Not Found"));

        ContactDataWrapper contactDataWrapper =
                ContactDataWrapper.builder()
                        .contactId(fetechedContactData.getContactId())
                        .contactType(fetechedContactData.getContactType())
                        .mobileNumber1(fetechedContactData.getMobileNumber1())
                        .mobileNumber2(fetechedContactData.getMobileNumber2())
                        .email(fetechedContactData.getEmail())
                        .build();

        return ResponseEntity.status(HttpStatus.OK).body(contactDataWrapper);
    }

    public ResponseEntity<List<ContactDataWrapper>> getAllConatactByUserId(long userId) throws UserNotFoundException {
        userDataRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with user id : " + userId + " not found"));

        List<ContactData> fetechedContactDataList = contactDataRepository.findAllByUserId(userId);

        List<ContactDataWrapper> fetechedContactDataWrapper = new ArrayList<>();

        for (ContactData contactData : fetechedContactDataList) {
            ContactDataWrapper contactDataWrapper =
                    ContactDataWrapper.builder()
                            .contactId(contactData.getContactId())
                            .contactType(contactData.getContactType())
                            .mobileNumber1(contactData.getMobileNumber1())
                            .mobileNumber2(contactData.getMobileNumber2())
                            .email(contactData.getEmail())
                            .build();
            fetechedContactDataWrapper.add(contactDataWrapper);
        }


        return ResponseEntity.status(HttpStatus.OK).body(fetechedContactDataWrapper);
    }

    public ResponseEntity deletecontactIdBycontactIdId(long contactId) throws AddressNotFoundException {
        contactDataRepository.findById(contactId).orElseThrow(() -> new AddressNotFoundException("Contact with ContactID : " + contactId + " Not Found"));
        contactDataRepository.deleteById(contactId);

        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    public ResponseEntity updateAddressById(long contactId, ContactDataWrapper contactDataWrapper) throws ContactNotFoundException {
        ContactData fetchedContactData = contactDataRepository.findById(contactId).orElseThrow(() -> new ContactNotFoundException("Contact with ContactID : " + contactId + " Not Found"));

        if (contactDataWrapper.getContactType() != null) {
            fetchedContactData.setContactType(contactDataWrapper.getContactType());
        }

        if (contactDataWrapper.getMobileNumber1() != null) {
            fetchedContactData.setMobileNumber1(contactDataWrapper.getMobileNumber1());
        }

        if (contactDataWrapper.getMobileNumber2() != null) {
            fetchedContactData.setMobileNumber2(contactDataWrapper.getMobileNumber2());
        }

        if (contactDataWrapper.getEmail() != null) {
            fetchedContactData.setEmail(contactDataWrapper.getEmail());
        }

        contactDataRepository.save(fetchedContactData);

        contactDataWrapper.setContactId(contactId);

        return ResponseEntity.status(HttpStatus.OK).body(contactDataWrapper);

    }

}
