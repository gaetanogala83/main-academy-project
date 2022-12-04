package com.academy.ws.controller;

import com.academy.ws.model.Order;
import com.academy.ws.service.ClientService;
import com.academy.ws.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/keepAlive")
    public ResponseEntity<String> keepAlive(){
        return new ResponseEntity<>("The ORDER Controller is ALIVE", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();

        if(allOrders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Object> getOrderById(@PathVariable Integer orderId) {

        Order order = orderService.getOrder(orderId);

        if(order == null)
            return new ResponseEntity<>(String.format("The resource with id %d doesn't exist!", orderId), HttpStatus.NOT_FOUND);
//                return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Object> getOrderByClientId(@PathVariable Integer clientId){

        List<Order> retrievedOrders = orderService.getAllOrdersByClientId(clientId);

        if(retrievedOrders == null || retrievedOrders.isEmpty())
            return new ResponseEntity<>(String.format("No one order for the client with id %d !", clientId), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(retrievedOrders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(@RequestBody Order order){

        Order savedOrder = orderService.saveOrder(order);

        if(savedOrder == null)
            return new ResponseEntity<>("An error occurred during the new Order saving. Retry!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateOrder(@RequestBody Order order){

        Order updatedOrder = orderService.updateOrder(order);

        if(updatedOrder == null)
            return new ResponseEntity<>("An error occurred during the Order updating. Retry!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(updatedOrder, HttpStatus.CREATED);
    }

}
