package com.academy.ws.controller;

import com.academy.ws.model.Order;
import com.academy.ws.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/keepAlive")
    public ResponseEntity<String> keepAlive(){
        return new ResponseEntity<>("I'm ALIVE", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();

        if(allOrders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
        try {
            Optional<Order> order = orderService.getOrder(orderId);
            return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
