package com.example.appregister.controller;

import com.example.appregister.entity.Client;
import com.example.appregister.entity.RegisterDTO;
import com.example.appregister.service.ClientService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    private List<Client> getUsers() {
        return clientService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Long id) {
        try {
            Client user = clientService.findClientById(id);
            clientService.deleteClient(user);
            logger.info(String.format("UserController message -> %s", "user with " + id + " deleted!!!"));
        } catch (RuntimeException e) {
            logger.info(String.format("UserController message -> %s", "user with " + id + " not found!!!"));
            throw new RuntimeException("User is empty!!!");
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> showUser(@PathVariable Long id) {
        try {
            Client user = clientService.findClientById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            logger.info(String.format("UserController message -> %s", "user with " + id + " not found!!!"));
            throw new RuntimeException("Empty user");
        }
    }

    @PostMapping("/save")
    public Client createUser(@RequestBody RegisterDTO client) {
        if (clientService.findClientByEmail(client.getEmail()) != null) {
            logger.info(String.format("UserController message -> %s", "user with " + client.getEmail() + " created!!!"));
            throw new RuntimeException("Can not");
        }
        logger.info(String.format("UserController message -> %s", "user with " + client.getEmail() + " saved!!!"));
        return clientService.saveClient(client);
    }

}
