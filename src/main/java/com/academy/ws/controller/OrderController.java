package com.academy.ws.controller;

import com.academy.ws.model.Order;
import com.academy.ws.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;


    @GetMapping("/keepAlive")
    public ResponseEntity<String> keepAlive(){
        LOGGER.info("Calling the KeepAlive API!");
        return new ResponseEntity<>("The ORDER Controller is ALIVE", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        LOGGER.info("Calling the getAllOrders API!");

        List<Order> allOrders = orderService.getAllOrders();

        if(allOrders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Object> getOrderById(@PathVariable Integer orderId) {
        LOGGER.info("Calling the getOrderById API!");

        Order order = orderService.getOrder(orderId);

        if(order == null)
            return new ResponseEntity<>(String.format("The resource with id %d doesn't exist!", orderId), HttpStatus.NOT_FOUND);
//                return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Object> getOrderByClientId(@PathVariable Integer clientId){
        LOGGER.info("Calling the getOrderByClientId API!");

        List<Order> retrievedOrders = orderService.getAllOrdersByClientId(clientId);

        if(retrievedOrders == null || retrievedOrders.isEmpty())
            return new ResponseEntity<>(String.format("No one order for the client with id %d !", clientId), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(retrievedOrders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(@RequestBody Order order){
        LOGGER.info("Calling the saveOrder API!");

        Order savedOrder = orderService.saveOrder(order);

        if(savedOrder == null)
            return new ResponseEntity<>("An error occurred during the new Order saving. Retry!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateOrder(@RequestBody Order order){
        LOGGER.info("Calling the updateOrder API!");

        Order updatedOrder = orderService.updateOrder(order);

        if(updatedOrder == null)
            return new ResponseEntity<>("An error occurred during the Order updating. Retry!", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(updatedOrder, HttpStatus.CREATED);
    }

}
