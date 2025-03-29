package org.nimol.springhomework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequest {
    @NotNull(message = "Event Name is required")
    @NotBlank(message = "Event Name cannot be blank")
    @Size(min = 2, max = 100, message = "Event Name must be between 2 and 15 characters")
    private String eventName;

    @NotNull(message = "Event Date is required")
    private LocalDate eventDate;

    @NotNull(message = "Venue ID is required")
    private Long venueId;

    @NotNull(message = "Attendee Name is required")
    private List<Long> attendeeIds;
}
