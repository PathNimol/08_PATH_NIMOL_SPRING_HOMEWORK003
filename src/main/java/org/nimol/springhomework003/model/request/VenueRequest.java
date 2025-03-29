package org.nimol.springhomework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueRequest {
    @NotNull(message = "Venue Name is required")
    @NotBlank(message = "Venue Name cannot be blank")
    @Size(min = 2, max = 100, message = "Venue Name must be between 2 and 100 characters")
    private String venueName;

    @NotNull(message = "Location is required")
    @NotBlank(message = "Location cannot be blank")
    @Size(min = 2, max = 100, message = "Location must be between 2 and 100 characters")
    private String location;

}
