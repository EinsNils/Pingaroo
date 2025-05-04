package de.pingaroo.backend.controller;

import de.pingaroo.backend.domain.dtos.EndpointDto;
import de.pingaroo.backend.mappers.EndpointMapper;
import de.pingaroo.backend.service.EndpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
