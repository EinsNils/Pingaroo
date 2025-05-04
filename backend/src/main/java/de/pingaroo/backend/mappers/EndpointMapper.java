package de.pingaroo.backend.mappers;

import de.pingaroo.backend.domain.dtos.CreateEndpointRequest;
import de.pingaroo.backend.domain.dtos.EndpointDto;
import de.pingaroo.backend.domain.entities.Endpoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EndpointMapper {
    
    @Mapping(target = "ownerId", source = "owner.id")
    EndpointDto toDto(Endpoint endpoint);
    
    @Mapping(target = "owner.id", source = "ownerId")
    Endpoint toDomain(EndpointDto endpointDto);
    
    //TODO: Add mapping for the ownerId 
    Endpoint toEntity(CreateEndpointRequest createEndpointRequest);
}
