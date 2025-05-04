package de.pingaroo.backend.repositories;

import de.pingaroo.backend.domain.entities.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, UUID> {
    List<Endpoint> findAllByOwnerId(UUID userId);

    boolean existsByNameIgnoreCase(String name);

    void deleteById(UUID id);
}
