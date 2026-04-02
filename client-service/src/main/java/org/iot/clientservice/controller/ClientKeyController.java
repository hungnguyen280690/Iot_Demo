package org.iot.clientservice.controller;

import org.iot.clientservice.model.ClientKey;
import org.iot.clientservice.repository.ClientKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/client-keys")
public class ClientKeyController {

    @Autowired
    private ClientKeyRepository clientKeyRepository;

    @GetMapping
    public ResponseEntity<?> getAllClientKeys() {
        return ResponseEntity.ok(clientKeyRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientKeyById(@PathVariable UUID id) {
        Optional<ClientKey> clientKey = clientKeyRepository.findById(id);
        if (clientKey.isPresent()) {
            return ResponseEntity.ok(clientKey.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createClientKey(@RequestBody ClientKey clientKey) {
        clientKey.setCreatedAt(System.currentTimeMillis());
        ClientKey savedClientKey = clientKeyRepository.save(clientKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClientKey);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClientKey(@PathVariable UUID id, @RequestBody ClientKey clientKeyDetails) {
        Optional<ClientKey> clientKey = clientKeyRepository.findById(id);
        if (clientKey.isPresent()) {
            ClientKey existingClientKey = clientKey.get();
            if (clientKeyDetails.getApiKey() != null) {
                existingClientKey.setApiKey(clientKeyDetails.getApiKey());
            }
            if (clientKeyDetails.getApiSecretHash() != null) {
                existingClientKey.setApiSecretHash(clientKeyDetails.getApiSecretHash());
            }
            if (clientKeyDetails.getIsActive() != null) {
                existingClientKey.setIsActive(clientKeyDetails.getIsActive());
            }
            ClientKey updatedClientKey = clientKeyRepository.save(existingClientKey);
            return ResponseEntity.ok(updatedClientKey);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClientKey(@PathVariable UUID id) {
        if (clientKeyRepository.existsById(id)) {
            clientKeyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api-key/{apiKey}")
    public ResponseEntity<?> getClientKeyByApiKey(@PathVariable String apiKey) {
        Optional<ClientKey> clientKey = clientKeyRepository.findByApiKey(apiKey);
        if (clientKey.isPresent()) {
            return ResponseEntity.ok(clientKey.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getClientKeysByClientId(@PathVariable UUID clientId) {
        return ResponseEntity.ok(clientKeyRepository.findByClientId(clientId));
    }

    @GetMapping("/{id}/active")
    public ResponseEntity<?> getActiveClientKey(@PathVariable UUID id) {
        Optional<ClientKey> clientKey = clientKeyRepository.findByIdAndIsActive(id, true);
        if (clientKey.isPresent()) {
            return ResponseEntity.ok(clientKey.get());
        }
        return ResponseEntity.notFound().build();
    }
}
