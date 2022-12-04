package com.academy.ws.service;

import com.academy.ws.model.Order;
import com.academy.ws.repository.OrderRepository;
import com.academy.ws.utils.WsUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrdersByClientId(Integer clientId) {

        Optional<List<Order>> orders = orderRepository.findByClient_Id(clientId);

        if(orders.isEmpty())
            return null;

        putClientIdCollection(orders.get());

        return orders.get();
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        orderRepository.findAll().forEach(orders::add);

        putClientIdCollection(orders);
        return orders;
    }

    public Order getOrder(Integer orderId) {

        Optional<Order> orderOpt = orderRepository.findById(orderId);

        if(orderOpt.isEmpty())
            return null;

        putClientId(orderOpt.get());

        return orderOpt.get();
    }

    public Order saveOrder(Order order){

        if(order.getClientId() == null)
            return null;

        return orderRepository.save(order);
    }

    public Order updateOrder(Order order){

        if(order.getId() == null)
            return null;

        Optional<Order> retrievedOrder = orderRepository.findById(order.getId());

        if(retrievedOrder.isEmpty())
            return null;

        Order orderToUpdate = retrievedOrder.get();
        WsUtils.copyProperties(order, orderToUpdate);

        Order savedOrder = orderRepository.save(orderToUpdate);

        putClientId(savedOrder);

        return savedOrder;
    }

    private void putClientIdCollection(List<Order> orders){
        orders.forEach(this::putClientId);
    }

    private void putClientId(Order order){

        if(order.getClient() != null)
            order.setClientId(order.getClient().getId());
    }

}
