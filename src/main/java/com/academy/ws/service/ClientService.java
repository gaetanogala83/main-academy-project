package com.academy.ws.service;

import com.academy.ws.model.Client;
import com.academy.ws.repository.ClientRepository;
import org.hibernate.id.IntegralDataTypeHolder;
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

    public void deleteClientById(Integer id){

        clientRepository.deleteById(id);
    }

    public Client saveOrUpdateClient(Client clientToSave){

        return clientRepository.save(clientToSave);
    }


}
