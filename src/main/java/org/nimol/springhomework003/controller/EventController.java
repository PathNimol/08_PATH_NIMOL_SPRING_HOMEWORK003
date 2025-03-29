package org.nimol.springhomework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nimol.springhomework003.model.entity.Event;
import org.nimol.springhomework003.model.request.EventRequest;
import org.nimol.springhomework003.model.response.ApiResponse;
import org.nimol.springhomework003.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Event Controller")
public class EventController {

    private final EventService eventService;

    @GetMapping
    @Operation(summary = "Get all Events")
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") Integer page,
                                                                 @RequestParam(defaultValue = "5") Integer size) {
        List<Event> events = eventService.getAllEvents(page, size);
        ApiResponse<List<Event>> apiResponse = ApiResponse.<List<Event>>builder()
                .message("All Event have been successfully FETCHED.")
                .payload(events)
                .status(OK)
                .build();
        return ResponseEntity.status(OK).body(apiResponse);
    }

    @GetMapping("/{event-id}")
    @Operation(summary = "Get an Event by ID")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable("event-id") Long eventId) {
        Event event = eventService.getEventById(eventId);
        ApiResponse<Event> apiResponse = ApiResponse.<Event>builder()
                .message("An Event with ID " + eventId + " has been FOUND")
                .payload(event)
                .status(OK)
                .build();
        return ResponseEntity.status(OK).body(apiResponse);
    }

    @PutMapping("/{event-id}")
    @Operation(summary = "Update an Event by ID")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@PathVariable("event-id") Long eventId,
                                                              @RequestBody @Valid EventRequest request) {
        Event event = eventService.updateEventById(eventId, request);
        ApiResponse<Event> apiResponse = ApiResponse.<Event>builder()
                .message("An Event with ID " + eventId + " has been UPDATED")
                .payload(event)
                .status(OK)
                .build();
        return ResponseEntity.status(OK).body(apiResponse);
    }

    @PostMapping
    @Operation(summary = "Insert an Event")
    public ResponseEntity<ApiResponse<Event>> insertEvent(@RequestBody @Valid EventRequest request) {
        Event event = eventService.insertEvent(request);
        ApiResponse<Event> apiResponse = ApiResponse.<Event>builder()
                .message("An Event has been ADDED successfully")
                .payload(event)
                .status(CREATED)
                .build();
        return ResponseEntity.status(CREATED).body(apiResponse);
    }

    @DeleteMapping("/{event-id}")
    @Operation(summary = "Delete an Event by ID")
    public ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable("event-id") Long eventId) {
        eventService.deleteEventById(eventId);
        ApiResponse<Event> apiResponse = ApiResponse.<Event>builder()
                .message("An Event has been DELETED")
                .payload(null)
                .status(OK)
                .build();
        return ResponseEntity.status(OK).body(apiResponse);
    }
}