package com.example.appregister.repository;

import com.example.appregister.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findClientById(Long id);
    Client findClientByEmail(String email);

}
