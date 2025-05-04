package de.pingaroo.backend.controller;

import de.pingaroo.backend.domain.dtos.EndpointDto;
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
    
    @GetMapping
    public ResponseEntity<List<EndpointDto>> listEndpoints(){
        return endpointService.listEndpoints();
    }
}
