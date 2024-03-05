package com.frost.gasgo.asynchandler.controller;


import com.frost.gasgo.asynchandler.service.AsyncService;
import com.frost.gasgo.commonlib.async.event.EventControllerResponse;
import com.frost.gasgo.commonlib.async.event.StatusEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
    @Autowired
    private AsyncService asyncService;


    @PostMapping("order/async/event")
    public ResponseEntity<EventControllerResponse> asyncResponse(@RequestBody StatusEvent event){
        return asyncService.publishAsync(event);
    }

}
