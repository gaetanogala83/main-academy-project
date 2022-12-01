package com.academy.ws.service;

import com.academy.ws.model.Order;
import com.academy.ws.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrdersByClientId(Integer customerId) {

        Optional<List<Order>> orders = orderRepository.findByClientId(customerId);

        return orders.orElse(null);
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    public Optional<Order> getOrder(Integer orderId) {
        return orderRepository.findById(orderId);
    }

}
