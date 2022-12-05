package com.academy.ws.service;

import com.academy.ws.model.Client;
import com.academy.ws.repository.ClientRepository;
import com.academy.ws.utils.WsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> retrieveAllClients(){

        return new ArrayList<Client>(){{
            clientRepository.findAll().forEach(this::add);
        }};
    }

    public Client retrieveClientById(Integer id){

        Optional<Client> clientOpt = clientRepository.findById(id);

        return clientOpt.orElse(null);
    }

    public Boolean deleteClientById(Integer id){

        Client client = retrieveClientById(id);
        if(client == null)
            return false;

        clientRepository.deleteById(id);

        return true;
    }

    public Client retrieveClientByNameSurname(String name, String surname){

        Optional<Client> client = clientRepository.findClientByNameAndSurname(name, surname);

        return client.orElse(null);
    }

    public Client saveClient(Client clientToSave){

        if(clientToSave.getName() == null || clientToSave.getSurname() == null)
            return null;

        Optional<Client> checked = clientRepository.findClientByNameAndSurname(clientToSave.getName(), clientToSave.getSurname());

        if(checked.isPresent())
            return null;

        return clientRepository.save(clientToSave);
    }

    public Client updateClient(Client client){

        if(client.getId() == null)
            return null;

        Optional<Client> retrievedClient = clientRepository.findById(client.getId());

        if(retrievedClient.isEmpty())
            return null;

        Client clientToUpdate = retrievedClient.get();
        WsUtils.copyProperties(client, clientToUpdate);

        return clientRepository.save(clientToUpdate);
    }

}
