package com.academy.ws.controller;

import com.academy.ws.model.Client;
import com.academy.ws.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/keepAlive")
    public ResponseEntity<String> keepAlive(){
        return new ResponseEntity<>("I'm ALIVE", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){

        List<Client> clients = clientService.retrieveAllClients();

        if(clients == null || clients.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer clientId) {

        Client client = clientService.retrieveClientById(clientId);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/{name}/{surname}")
    public ResponseEntity<Client> getClientByNameAndSurname(@PathVariable String name, @PathVariable String surname) {

        Client client = clientService.retrieveClientByNameSurname(name, surname);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer clientId) {

        clientService.deleteClientById(clientId);

        Client deleteClient = clientService.retrieveClientById(clientId);

        if(deleteClient != null)
            return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {

        Client client = clientService.saveClient(newClient);

        if(client == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Client clientUpdated = clientService.updateClient(client);
        return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
    }
    
}
