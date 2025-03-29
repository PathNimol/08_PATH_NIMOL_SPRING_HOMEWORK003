package org.nimol.springhomework003.model.request;

import jakarta.validation.constraints.Email;
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
public class AttendeeRequest {
    @NotNull(message = "Attendee Name is required")
    @NotBlank(message = "Attendee Name cannot be blank")
    @Size(min = 2, max = 100, message = "Attendee Name must be between 2 and 15 characters")
    private String attendeeName;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address")
    private String email;
}
