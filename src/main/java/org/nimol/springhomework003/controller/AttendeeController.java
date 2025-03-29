package org.nimol.springhomework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nimol.springhomework003.model.entity.Attendee;
import org.nimol.springhomework003.model.request.AttendeeRequest;
import org.nimol.springhomework003.model.response.ApiResponse;
import org.nimol.springhomework003.service.AttendeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
@Tag(name = "Attendee Controller")
public class AttendeeController {

    private final AttendeeService attendeeService;

    //Get all Attendees
    @GetMapping
    @Operation(summary = "Get all Attendees")
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@RequestParam(defaultValue = "1") Integer page,
                                                                       @RequestParam(defaultValue = "10") Integer size) {
        List<Attendee> attendees = attendeeService.getAllAttendees(page, size);
        ApiResponse<List<Attendee>> apiResponse = ApiResponse.<List<Attendee>>builder()
                .message("All Attendees have been successfully FETCHED.")
                .payload(attendees)
                .status(OK)
                .build();
        return ResponseEntity.status(OK).body(apiResponse);
    }

    //Get an Attendee by ID
    @GetMapping("/{attendee-id}")
    @Operation(summary = "Get an Attendee by ID")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Long attendeeId) {
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);
        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .message("Attendee with ID" + attendeeId + " has been successfully FOUNDED.")
                .payload(attendee)
                .status(OK)
                .build();
        return ResponseEntity.status(FOUND).body(apiResponse);
    }

    //Update an Attendee by ID
    @PutMapping("/{attendee-id}")
    @Operation(summary = "Update an Attendee by ID")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(@PathVariable("attendee-id") Long attendeeId, @RequestBody @Valid AttendeeRequest request) {
        Attendee attendee = attendeeService.updateAttendeeById(attendeeId, request);
        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .message("Attendee with ID " + attendeeId + " has been UPDATED successfully!")
                .payload(attendee)
                .status(OK)
                .build();
        return ResponseEntity.status(OK).body(apiResponse);
    }

    //Create new an Attendee
    @PostMapping
    @Operation(summary = "Create a new Attendee")
    public ResponseEntity<ApiResponse<Attendee>> createAttendee(@RequestBody @Valid AttendeeRequest request) {
        Attendee attendee = attendeeService.createAttendee(request);
        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .message("Attendee has been CREATED successfully!")
                .payload(attendee)
                .status(CREATED)
                .build();
        return ResponseEntity.status(CREATED).body(apiResponse);
    }

    //Delete a Attendee by ID
    @DeleteMapping("/{attendee-id}")
    @Operation(summary = "Delete a Attendee by ID")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById(@PathVariable("attendee-id") Long attendeeId) {
        attendeeService.deleteAttendeeById(attendeeId);
        ApiResponse<Attendee> apiResponse = ApiResponse.<Attendee>builder()
                .message("Attendee ID " + attendeeId +  " has been DELETED!")
                .payload(null)
                .status(OK)
                .build();
        return ResponseEntity.status(OK).body(apiResponse);
    }
}
