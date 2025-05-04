package de.pingaroo.backend.domain.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEndpointRequest {

  @NotBlank(message = "Endpoint name is required")
  @Size(min = 2, max = 50, message = "Endpoint name must be between {min} and {max} characters long")
  @Pattern(
          regexp = "^[\\w\\s-]+$",
          message = "Endpoint name can only contain letters, numbers, spaces and hyphens")
  private String name;

  @NotBlank(message = "Description is required")
  @Size(min = 1, max = 255, message = "Description must be between {min} and {max} characters long")
  private String description;

  @NotBlank(message = "URL is required")
  @Size(max = 2048, message = "URL must not exceed {max} characters")
  @Pattern(
          regexp = "^(https?://).+",
          message = "URL must start with http:// or https://")
  private String url;

  @NotNull(message = "Check interval is required")
  @Min(value = 1, message = "Check interval must be at least {value} minute")
  @Max(value = 1440, message = "Check interval must not exceed {value} minutes")
  private Integer checkInterval;

  @NotNull(message = "Owner ID is required")
  private UUID ownerId;
}

