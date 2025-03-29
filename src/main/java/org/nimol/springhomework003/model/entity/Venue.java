package org.nimol.springhomework003.model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venue {
    private Long venueId;
    private String venueName;
    private String location;
}
