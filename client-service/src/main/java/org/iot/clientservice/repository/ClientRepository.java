package org.iot.clientservice.repository;

import org.iot.clientservice.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {
    Optional<Client> findByName(String name);
    Optional<Client> findByStatus(String status);
}
