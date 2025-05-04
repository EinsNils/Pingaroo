package de.pingaroo.backend.service;

import de.pingaroo.backend.domain.entities.Endpoint;
import java.util.List;
import java.util.UUID;

public interface EndpointService {
    
    List<Endpoint> listEndpoints();
    
    Endpoint createEndpoint(Endpoint endpoint);
    
    void deleteEndpoint(UUID id);
}
