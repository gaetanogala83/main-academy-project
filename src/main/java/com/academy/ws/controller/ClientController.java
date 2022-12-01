package com.academy.ws.controller;

import com.academy.ws.model.Client;
import com.academy.ws.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
