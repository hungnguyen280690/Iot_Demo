package org.iot.clientservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.Set;

@Table("clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "status")
    private String status = "ACTIVE";
    
    @Column(name = "max_cameras")
    private Integer maxCameras = 10;
    
    @Column(name = "created_at", updatable = false)
    private Long createdAt;
    
    private Set<ClientKey> clientKeys;
    

    
    public Client(String name, String status, Integer maxCameras, Long createdAt) {
        this.name = name;
        this.status = status != null ? status : "ACTIVE";
        this.maxCameras = maxCameras != null ? maxCameras : 10;
        this.createdAt = createdAt;
    }
 
}
