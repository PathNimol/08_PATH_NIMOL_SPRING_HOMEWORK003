package org.nimol.springhomework003.model.entity;

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
public class Event {
    private Long eventId;
    private String eventName;
    private LocalDate eventDate;
    private Venue venue;
    private List<Attendee> attendees;
}
