userhub microservices for gasgo project
API
  user/
    @PostMapping("adduser")
    @GetMapping("getUser/{userId}")
    
  address/
    @PostMapping("addAdress/{userId}")
    @GetMapping("getAddressByAddressId/{addressId}")
    @GetMapping("getAllAddressByUserId/{userId}")
    @DeleteMapping("deleteAddressByAddressId/{addressId}")
    @PatchMapping("updateAddressById/{addressId}")
    
  contact/
    @PostMapping("addContact/{userId}")
    @GetMapping("getContactByContactId/{contactId}")
    @GetMapping("getAllContactByUserId/{userId}")
    @DeleteMapping("deleteContactIdByContactIdId/{contactId}")
    @PatchMapping("updateContactById/{ContactId}")
  
