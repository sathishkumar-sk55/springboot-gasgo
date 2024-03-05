package com.frost.gasgo.ordermanager.controller;


import com.frost.gasgo.ordermanager.customexpection.AddressNotFoundException;
import com.frost.gasgo.ordermanager.customexpection.ContactNotFoundException;
import com.frost.gasgo.ordermanager.customexpection.OrderNotFoundException;
import com.frost.gasgo.ordermanager.customexpection.UserNotFoundException;
import com.frost.gasgo.ordermanager.entity.RefilOrder;
import com.frost.gasgo.ordermanager.service.OrderPlacementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;

@RestController
@RequestMapping("order")
public class OrderPlacementController {


    @Autowired
    private OrderPlacementServiceImpl orderPlacementService;

    //post: place order
    @PostMapping("PlaceOrder")
    public ResponseEntity placeOrder(@RequestBody RefilOrder order) throws UserNotFoundException, AddressNotFoundException, ContactNotFoundException, ConnectException {
        return orderPlacementService.placeOrder(order);
    }

    //get :  oget order status
    @GetMapping("status/{orderId}")
    public ResponseEntity getOrderStatus(@PathVariable Long orderId) throws OrderNotFoundException {
        return orderPlacementService.getOrderStatus(orderId);
    }

    //cancel : cancel order
}
