package org.nimol.springhomework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nimol.springhomework003.model.entity.Venue;
import org.nimol.springhomework003.model.request.VenueRequest;
import org.nimol.springhomework003.model.response.ApiResponse;
import org.nimol.springhomework003.service.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/venues")
@RequiredArgsConstructor
@Tag(name = "Venue Controller")
public class VenueController {

    private final VenueService venueService;

    //Get all Venues
    @GetMapping
    @Operation(summary = "Get all Venues")
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@RequestParam(defaultValue = "1") Integer page,
                                                                 @RequestParam(defaultValue = "10") Integer size) {
        List<Venue> venues = venueService.getAllVenues(page, size);
        ApiResponse<List<Venue>> apiResponse = ApiResponse.<List<Venue>>builder()
                .message("All Venue have been successfully FETCHED.")
                .payload(venues)
                .status(OK)
                .build();
        return ResponseEntity.status(OK).body(apiResponse);
    }

    //Get a Venue by ID
    @GetMapping("/{venue-id}")
    @Operation(summary = "Get a Venue by ID")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") Long venueId) {
        Venue venue = venueService.getVenueById(venueId);
        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("Venue with ID " + venueId + " has been successfully FOUNDED.")
                .payload(venue)
                .status(FOUND)
                .build();
        return ResponseEntity.status(FOUND).body(apiResponse);
    }

    //Update a venue by ID
    @PutMapping("/{venue-id}")
    @Operation(summary = "Update a Venue by ID")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@PathVariable("venue-id") Long venueId,
                                                              @RequestBody @Valid VenueRequest request){
        Venue venue = venueService.updateVenueById(venueId, request);
        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("Venue with ID " + venueId + " has been UPDATED successfully!")
                .payload(venue)
                .status(OK)
                .build();
        return ResponseEntity.status(OK).body(apiResponse);
    }

    //Create new a Venue
    @PostMapping
    @Operation(summary = "Create a new Venue")
    public ResponseEntity<ApiResponse<Venue>> createVenue(@RequestBody @Valid VenueRequest request) {
        Venue venue = venueService.createVenue(request);
        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("Venue has been CREATED successfully")
                .payload(venue)
                .status(CREATED)
                .build();
        return ResponseEntity.status(CREATED).body(apiResponse);
    }

    //Delete a Venue by ID
    @DeleteMapping("/{venue-id}")
    @Operation(summary = "Delete a Venue by ID")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") Long venueId) {
        venueService.deleteVenueById(venueId);
        ApiResponse<Venue> apiResponse = ApiResponse.<Venue>builder()
                .message("Venue has been DELETED!")
                .payload(null)
                .status(NO_CONTENT)
                .build();
        return ResponseEntity.status(NO_CONTENT).body(apiResponse);
    }
}
