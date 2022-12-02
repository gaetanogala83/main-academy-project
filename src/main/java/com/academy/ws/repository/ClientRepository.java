package com.academy.ws.repository;

import com.academy.ws.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    Optional<Client> findClientByNameAndSurname(String name, String surname);
}
