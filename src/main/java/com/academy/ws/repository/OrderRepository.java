package com.academy.ws.repository;

import com.academy.ws.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    Optional<List<Order>> findByClient_Id(Integer client_id);

}
