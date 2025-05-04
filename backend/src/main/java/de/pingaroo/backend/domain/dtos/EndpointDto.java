package de.pingaroo.backend.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndpointDto {

  private UUID id;
  private String name;
  private int checkInterval;
  private boolean isActive;
}
