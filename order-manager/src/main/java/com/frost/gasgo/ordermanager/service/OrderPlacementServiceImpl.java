package com.frost.gasgo.ordermanager.service;


import com.frost.gasgo.ordermanager.config.RestTemplateBean;
import com.frost.gasgo.ordermanager.customexpection.AddressNotFoundException;
import com.frost.gasgo.ordermanager.customexpection.ContactNotFoundException;
import com.frost.gasgo.ordermanager.customexpection.OrderNotFoundException;
import com.frost.gasgo.ordermanager.customexpection.UserNotFoundException;
import com.frost.gasgo.ordermanager.entity.RefilOrder;
import com.frost.gasgo.ordermanager.externalclass.AddressDataWrapper;
import com.frost.gasgo.ordermanager.externalclass.ContactDataWrapper;
import com.frost.gasgo.ordermanager.externalclass.UserDataWrapper;
import com.frost.gasgo.ordermanager.repository.RefilOrderRepository;
import com.frost.gasgo.ordermanager.util.DateConversion;
import com.frost.gasgo.ordermanager.wrapper.ExternalResponseWrapper;
import com.frost.gasgo.ordermanager.wrapper.RefilOrderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.Date;

@Service
public class OrderPlacementServiceImpl {
    private RestTemplate userhubRestTemplate;

    @Autowired
    private ExternalSystemRequestImpl externalSystemRequest;

    @Autowired
    private RefilOrderRepository refilOrderRepository;

    private RefilOrderWrapper refilOrderWrapper;

    public OrderPlacementServiceImpl(@Value("${userhub-service.base.url}") String url, RestTemplateBuilder restTemplateBuilder){
        this.userhubRestTemplate =
                restTemplateBuilder
                        .rootUri(url)
                        .build();
    }


    private ResponseEntity<UserDataWrapper> getUserFromUserhub(Long userId){
        return userhubRestTemplate.getForEntity("/user/getUser/{userId}", UserDataWrapper.class,userId);
    }

    private ResponseEntity<AddressDataWrapper> getAddressFromUserhub(Long addressId) throws AddressNotFoundException {
        return userhubRestTemplate.getForEntity("/address/getAddressByAddressId/{addressId}", AddressDataWrapper.class,addressId);
    }

    private ResponseEntity<ContactDataWrapper> getContactFromUserhub(Long contactId){
        return userhubRestTemplate.getForEntity("/contact/getContactByContactId/{contactId}", ContactDataWrapper.class,contactId);
    }
    public ResponseEntity<RefilOrder> placeOrder(RefilOrder order) throws UserNotFoundException, AddressNotFoundException, ContactNotFoundException, ConnectException {

        ResponseEntity<UserDataWrapper> userDataWrapperResponseEntity = null;
        ResponseEntity<AddressDataWrapper> addressDataWrapperResponseEntity = null;
        ResponseEntity<ContactDataWrapper> contactDataWrapperResponseEntity = null;

        try {


            //get user
            userDataWrapperResponseEntity = getUserFromUserhub(order.getUserId());

            //get address
            addressDataWrapperResponseEntity = getAddressFromUserhub(order.getAddressId());

            //get contact

            contactDataWrapperResponseEntity = getContactFromUserhub(order.getContactId());

        }
        catch (HttpClientErrorException httpClientErrorException){
            String exceptionString = httpClientErrorException.toString();
            if (exceptionString.contains("NOT_FOUND")){
                if (exceptionString.contains("User")){
                    throw new UserNotFoundException("User is invalid");
                }else if (exceptionString.contains("Address")){
                    throw new AddressNotFoundException("Address is invalid");
                }else if (exceptionString.contains("Contact")){
                    throw new ContactNotFoundException("Contact is invalid");
                }
            }
            else {
                throw httpClientErrorException;
            }
        }
        catch (ResourceAccessException exception){
            throw new ResourceAccessException("Connection failed : userhub");
        }

        order.setStatus("PLACED");
        order.setEstimatedDeliveryDate(new Date());

        RefilOrder placedRefilOrder = refilOrderRepository.save(order);

        RefilOrderWrapper externalRequestPayload =
                RefilOrderWrapper
                        .builder()
                        .orderId(placedRefilOrder.getOrderId())
                        .status(placedRefilOrder.getStatus())
                        .orderType(placedRefilOrder.getOrderType())
                        .userData(userDataWrapperResponseEntity.getBody())
                        .addressData(addressDataWrapperResponseEntity.getBody())
                        .contactData(contactDataWrapperResponseEntity.getBody())
                        .build();

        ResponseEntity<ExternalResponseWrapper> externalSystemResponse = externalSystemRequest.sendOrderRequestToExternalSystem(externalRequestPayload);

        if (externalSystemResponse.getStatusCode() == HttpStatus.CREATED){
            placedRefilOrder.setEstimatedDeliveryDate(DateConversion.convertStringToDate(externalSystemResponse.getBody().getEstimatedDeliveryDate()));
            placedRefilOrder.setStatus(externalSystemResponse.getBody().getStatus());
            refilOrderRepository.save(placedRefilOrder);

        }



        return ResponseEntity.status(HttpStatus.CREATED).body(placedRefilOrder);
    }

    public ResponseEntity<RefilOrder> getOrderStatus(Long orderId) throws OrderNotFoundException {
        RefilOrder order = refilOrderRepository.findById(orderId).orElseThrow(() ->new OrderNotFoundException("Order Not Found"));

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
