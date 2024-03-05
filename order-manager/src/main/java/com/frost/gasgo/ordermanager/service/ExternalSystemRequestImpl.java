package com.frost.gasgo.ordermanager.service;

import com.fasterxml.jackson.core.util.RequestPayload;
import com.frost.gasgo.ordermanager.externalclass.AddressDataWrapper;
import com.frost.gasgo.ordermanager.externalclass.ContactDataWrapper;
import com.frost.gasgo.ordermanager.externalclass.UserDataWrapper;
import com.frost.gasgo.ordermanager.wrapper.ExternalResponseWrapper;
import com.frost.gasgo.ordermanager.wrapper.RefilOrderWrapper;
import lombok.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;




@Service
public class ExternalSystemRequestImpl {


    private RestTemplate externalSystemRequestTemplate;

    @Value("${external-system.send-request}")
    private String sendRequest;

    public ExternalSystemRequestImpl(@Value("${external-system.base.url}") String url, RestTemplateBuilder restTemplateBuilder){
        this.externalSystemRequestTemplate =
                restTemplateBuilder
                        .rootUri(url)
                        .build();
    }

    public ResponseEntity<ExternalResponseWrapper> sendOrderRequestToExternalSystem(RefilOrderWrapper externalRequestPayload){

        if (sendRequest.contains("N")){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ExternalResponseWrapper("RECIEVED","01-05-2025"));
        }

        ResponseEntity<ExternalResponseWrapper> responseEntity = externalSystemRequestTemplate.postForEntity("/orderplace/external",externalRequestPayload,ExternalResponseWrapper.class);
        return responseEntity;
    }
}
