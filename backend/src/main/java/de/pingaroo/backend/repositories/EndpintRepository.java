package de.pingaroo.backend.repositories;

import de.pingaroo.backend.domain.entities.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EndpintRepository extends JpaRepository<Endpoint, UUID> {}
