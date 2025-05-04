package de.pingaroo.backend.service;

import de.pingaroo.backend.domain.entities.Endpoint;

import java.util.List;

public interface EndpointService {
    List<Endpoint> listEndpoints();
}
