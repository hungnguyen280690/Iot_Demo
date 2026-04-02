package org.iot.clientservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.UUID;

@Table("client_keys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientKey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "client_id", nullable = false)
    private UUID clientId;
    
    @Column(name = "api_key", unique = true, nullable = false)
    private String apiKey;
    
    @Column(name = "api_secret_hash", nullable = false)
    private String apiSecretHash;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "created_at", updatable = false)
    private Long createdAt;

    
    public ClientKey(UUID clientId, String apiKey, String apiSecretHash, Boolean isActive, Long createdAt) {
        this.clientId = clientId;
        this.apiKey = apiKey;
        this.apiSecretHash = apiSecretHash;
        this.isActive = isActive != null ? isActive : true;
        this.createdAt = createdAt;
    }
    

}
