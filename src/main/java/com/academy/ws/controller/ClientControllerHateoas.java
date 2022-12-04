package com.academy.ws.controller;

import com.academy.ws.model.Client;
import com.academy.ws.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "api/v2/clients")
public class ClientControllerHateoas {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientControllerHateoas.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("/keepAlive")
    public ResponseEntity<String> keepAlive(){
        LOGGER.info("Calling the KeepAlive API V2!");
        return new ResponseEntity<>("The CLIENT Controller V2 is ALIVE", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Client>> getAllClients(){
        LOGGER.info("Calling the getAllClients API V2!");

        List<Client> clients = clientService.retrieveAllClients();

        if(clients == null || clients.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        clients.forEach(client -> {
            Link selfLink = linkTo(ClientController.class).slash(client.getId()).withSelfRel();
            client.add(selfLink);
            if(client.getOrders() != null && !client.getOrders().isEmpty()){
                client.getOrders().forEach(order -> {
                    Link orderLink = linkTo(OrderController.class).slash(order.getId()).withSelfRel();
                    order.add(orderLink);
                });
            }
        });

        CollectionModel<Client> result = CollectionModel.of(clients);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Client>> createClient(@Valid @RequestBody Client newClient) {
        LOGGER.info("Calling the createClient API V2!");

        Client client = clientService.saveClient(newClient);

        if(client == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        Link selfLink = linkTo(ClientController.class).slash(client.getId()).withSelfRel();
        client.add(selfLink);

        return new ResponseEntity<>(EntityModel.of(client), HttpStatus.CREATED);
    }

   /* @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer clientId) {
        LOGGER.info("Calling the getClientById API V2!");

        Client client = clientService.retrieveClientById(clientId);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{name}/{surname}")
    public ResponseEntity<Client> getClientByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        LOGGER.info("Calling the getClientByNameAndSurname API V2!");

        Client client = clientService.retrieveClientByNameSurname(name, surname);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer clientId) {
        LOGGER.info("Calling the deleteClient API V2!");

        clientService.deleteClientById(clientId);

        Client deleteClient = clientService.retrieveClientById(clientId);

        if(deleteClient != null)
            return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {
        LOGGER.info("Calling the createClient API V2!");

        Client client = clientService.saveClient(newClient);

        if(client == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        LOGGER.info("Calling the updateClient API V2!");

        Client clientUpdated = clientService.updateClient(client);
        return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
    }*/
    
}
