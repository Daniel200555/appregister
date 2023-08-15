package com.example.appregister.service;

import com.example.appregister.entity.Client;
import com.example.appregister.entity.RegisterDTO;

import java.util.List;

public interface ClientService {
    Client findClientByEmail(String email);
    Client findClientById(Long id);
    Client saveClient(RegisterDTO client);
    void deleteClient(Client client);
    List<Client> findAll();
}
