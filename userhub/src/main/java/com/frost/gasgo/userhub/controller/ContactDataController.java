package com.frost.gasgo.userhub.controller;

import com.frost.gasgo.userhub.customexpection.AddressNotFoundException;
import com.frost.gasgo.userhub.customexpection.ContactNotFoundException;
import com.frost.gasgo.userhub.customexpection.UserNotFoundException;
import com.frost.gasgo.userhub.entity.AddressData;
import com.frost.gasgo.userhub.entity.ContactData;
import com.frost.gasgo.userhub.service.ContactDataServiceImpl;
import com.frost.gasgo.userhub.wrapper.AddressDataWrapper;
import com.frost.gasgo.userhub.wrapper.ContactDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contact")
public class ContactDataController {

    @Autowired
    private ContactDataServiceImpl contactDataService;

    @PostMapping("addContact/{userId}")
    public ResponseEntity<ContactDataWrapper> addContact(@PathVariable long userId, @RequestBody ContactData contactData) throws UserNotFoundException {
        return contactDataService.addContact(userId, contactData);
    }

    @GetMapping("getContactByContactId/{contactId}")
    public ResponseEntity<ContactDataWrapper> getAddressByAddressId(@PathVariable long contactId) throws ContactNotFoundException {
        return contactDataService.getConatactByContactId(contactId);
    }

    @GetMapping("getAllContactByUserId/{userId}")
    public ResponseEntity<List<ContactDataWrapper>> getAllConatactByUserId(@PathVariable long userId) throws UserNotFoundException {
        return contactDataService.getAllConatactByUserId(userId);
    }

    @DeleteMapping("deleteContactIdByContactIdId/{contactId}")
    public ResponseEntity deleteContactIdByContactIdId(@PathVariable long contactId) throws AddressNotFoundException {
        return contactDataService.deletecontactIdBycontactIdId(contactId);
    }

    @PatchMapping("updateContactById/{ContactId}")
    public ResponseEntity updateAddressById(@PathVariable long ContactId, @RequestBody ContactDataWrapper contactDataWrapper) throws AddressNotFoundException, ContactNotFoundException {
        return contactDataService.updateAddressById(ContactId, contactDataWrapper);
    }

}
