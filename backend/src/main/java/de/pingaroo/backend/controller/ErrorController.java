package de.pingaroo.backend.controller;

import de.pingaroo.backend.domain.dtos.EndpointErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<EndpointErrorResponse> handleException(Exception ex) {
        log.error("Caught exception: ", ex);
        EndpointErrorResponse errorResponse = EndpointErrorResponse.builder()
                .StatusCode(500)
                .Message("An unexpected error occurred")
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<EndpointErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Caught IllegalArgumentException: ", ex);
        EndpointErrorResponse errorResponse = EndpointErrorResponse.builder()
                .StatusCode(400)
                .Message("Invalid input provided")
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
}
