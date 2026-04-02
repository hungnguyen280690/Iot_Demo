package org.iot.clientservice.controller;

import org.iot.clientservice.model.Client;
import org.iot.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        client.setCreatedAt(System.currentTimeMillis());
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable UUID id, @RequestBody Client clientDetails) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client existingClient = client.get();
            if (clientDetails.getName() != null) {
                existingClient.setName(clientDetails.getName());
            }
            if (clientDetails.getStatus() != null) {
                existingClient.setStatus(clientDetails.getStatus());
            }
            if (clientDetails.getMaxCameras() != null) {
                existingClient.setMaxCameras(clientDetails.getMaxCameras());
            }
            Client updatedClient = clientRepository.save(existingClient);
            return ResponseEntity.ok(updatedClient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable UUID id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getClientByName(@PathVariable String name) {
        Optional<Client> client = clientRepository.findByName(name);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getClientByStatus(@PathVariable String status) {
        Optional<Client> client = clientRepository.findByStatus(status);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }
}
