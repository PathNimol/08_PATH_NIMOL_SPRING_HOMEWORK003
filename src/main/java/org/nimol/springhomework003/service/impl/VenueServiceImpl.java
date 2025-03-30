package org.nimol.springhomework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.nimol.springhomework003.exception.NotFoundException;
import org.nimol.springhomework003.model.entity.Venue;
import org.nimol.springhomework003.model.request.VenueRequest;
import org.nimol.springhomework003.repository.VenueRepository;
import org.nimol.springhomework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        page = (page - 1) * size;
        return venueRepository.getAllVenues(page, size);
    }

    @Override
    public Venue getVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Venue ID " + venueId + " not found");
        }
        return venue;
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest request) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Venue ID " + venueId + " not found for update");
        }
        return venueRepository.updateVenueById(venueId, request);
    }


    @Override
    public Venue createVenue(VenueRequest request) {
        return venueRepository.createVenue(request);
    }

    @Override
    public void deleteVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Venue ID " + venueId + " not found for deletion");
        }
        venueRepository.deleteVenueById(venueId);
    }
}
