package com.example.appregister.service.impl;

import com.example.appregister.entity.Client;
import com.example.appregister.entity.RegisterDTO;
import com.example.appregister.repository.ClientRepository;
import com.example.appregister.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Client findClientByEmail(String email) {
        return userRepository.findClientByEmail(email);
    }

    @Override
    public Client findClientById(Long id) {
        return userRepository.findClientById(id);
    }

    @Override
    public List<Client> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Client saveClient(RegisterDTO register) {
        Client client = new Client();
        client.setEmail(register.getEmail());
        client.setFirstname(register.getFirstname());
        client.setLastname(register.getLastname());
        client.setPassword(passwordEncoder.encode(register.getPassword()));
        return userRepository.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        userRepository.delete(client);
    }
}
