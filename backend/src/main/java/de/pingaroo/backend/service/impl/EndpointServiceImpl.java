package de.pingaroo.backend.service.impl;

import de.pingaroo.backend.domain.entities.Endpoint;
import de.pingaroo.backend.repositories.EndpointRepository;
import de.pingaroo.backend.service.EndpointService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EndpointServiceImpl implements EndpointService {

  private final EndpointRepository endpointRepository;

  @Override
  public List<Endpoint> listEndpoints() {
    return endpointRepository.findAll();
  }

  @Override
  @Transactional
  public Endpoint createEndpoint(Endpoint endpoint) {

    if (endpointRepository.existsByNameIgnoreCase(endpoint.getName())) {
      throw new IllegalArgumentException(
          "Endpoint already exists with name: " + endpoint.getName());
    }

    return endpointRepository.save(endpoint);
  }

  @Override
  public void deleteEndpoint(UUID id) {
    Optional<Endpoint> endpoint = endpointRepository.findById(id);
    if (endpoint.isEmpty()) {
      throw new IllegalStateException("Endpoint not found with id: " + id);
    }

    endpointRepository.deleteById(id);
  }
}
