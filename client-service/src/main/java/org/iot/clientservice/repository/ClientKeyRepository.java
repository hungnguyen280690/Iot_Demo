package org.iot.clientservice.repository;

import org.iot.clientservice.model.ClientKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

@Repository
public interface ClientKeyRepository extends CrudRepository<ClientKey, UUID> {
    Optional<ClientKey> findByApiKey(String apiKey);
    List<ClientKey> findByClientId(UUID clientId);
    Optional<ClientKey> findByIdAndIsActive(UUID id, Boolean isActive);
}
