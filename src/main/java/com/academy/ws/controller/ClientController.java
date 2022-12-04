package com.academy.ws.controller;

import com.academy.ws.model.Client;
import com.academy.ws.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/v1/clients")
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("/keepAlive")
    public ResponseEntity<String> keepAlive(){
        LOGGER.info("Calling the KeepAlive API!");
        return new ResponseEntity<>("The CLIENT Controller is ALIVE", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        LOGGER.info("Calling the getAllClients API!");

        List<Client> clients = clientService.retrieveAllClients();

        if(clients == null || clients.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }


    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer clientId) {
        LOGGER.info("Calling the getClientById API!");

        Client client = clientService.retrieveClientById(clientId);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{name}/{surname}")
    public ResponseEntity<Client> getClientByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        LOGGER.info("Calling the getClientByNameAndSurname API!");

        Client client = clientService.retrieveClientByNameSurname(name, surname);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer clientId) {
        LOGGER.info("Calling the deleteClient API!");

        clientService.deleteClientById(clientId);

        Client deleteClient = clientService.retrieveClientById(clientId);

        if(deleteClient != null)
            return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {
        LOGGER.info("Calling the createClient API!");

        Client client = clientService.saveClient(newClient);

        if(client == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        LOGGER.info("Calling the updateClient API!");

        Client clientUpdated = clientService.updateClient(client);
        return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
    }
    
}
