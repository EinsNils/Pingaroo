package de.pingaroo.backend.controller;

import de.pingaroo.backend.domain.dtos.CreateEndpointRequest;
import de.pingaroo.backend.domain.dtos.EndpointDto;
import de.pingaroo.backend.domain.entities.Endpoint;
import de.pingaroo.backend.mappers.EndpointMapper;
import de.pingaroo.backend.service.EndpointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/endpoints")
@RequiredArgsConstructor
public class EndpointController {

  private final EndpointService endpointService;
  private final EndpointMapper endpointMapper;

  @GetMapping
  public ResponseEntity<List<EndpointDto>> listEndpoints() {
    List<EndpointDto> endpoints =
        endpointService.listEndpoints().stream().map(endpointMapper::toDto).toList();
    
    return ResponseEntity.ok(endpoints);
  }

  @PostMapping
  public ResponseEntity<EndpointDto> createEndpoint(
      @Valid @RequestBody CreateEndpointRequest createEndpointRequest) {

    Endpoint endpointToEntity = endpointMapper.toEntity(createEndpointRequest);
    Endpoint savedEndpoint = endpointService.createEndpoint(endpointToEntity);

    return new ResponseEntity<>(endpointMapper.toDto(savedEndpoint), HttpStatus.CREATED);
  }
  
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteEndpoint(@PathVariable UUID id) {
    endpointService.deleteEndpoint(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
