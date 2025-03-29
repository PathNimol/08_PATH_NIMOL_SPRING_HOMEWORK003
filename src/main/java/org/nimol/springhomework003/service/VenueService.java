package org.nimol.springhomework003.service;

import org.nimol.springhomework003.model.entity.Venue;
import org.nimol.springhomework003.model.request.VenueRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VenueService {
    List<Venue> getAllVenues(Integer page, Integer size);

    Venue getVenueById(Long venueId);

    Venue updateVenueById(Long venueId, VenueRequest request);

    Venue createVenue(VenueRequest request);

    void deleteVenueById(Long venueId);
}
