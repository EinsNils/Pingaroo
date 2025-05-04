package de.pingaroo.backend.service.impl;

import de.pingaroo.backend.domain.entities.Endpoint;
import de.pingaroo.backend.repositories.EndpointRepository;
import de.pingaroo.backend.service.EndpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EndpointServiceImpl implements EndpointService {
    
    private final EndpointRepository endpointRepository; 
    
    @Override
    public List<Endpoint> listEndpoints() {
        return endpointRepository.findAll();
    }
    
}
