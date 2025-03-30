package org.nimol.springhomework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.nimol.springhomework003.exception.NotFoundException;
import org.nimol.springhomework003.model.entity.Event;
import org.nimol.springhomework003.model.request.EventRequest;
import org.nimol.springhomework003.repository.AttendeeRepository;
import org.nimol.springhomework003.repository.EventAttendeeRepository;
import org.nimol.springhomework003.repository.EventRepository;
import org.nimol.springhomework003.repository.VenueRepository;
import org.nimol.springhomework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final AttendeeRepository attendeeRepository;
    private final VenueRepository venueRepository;

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        page = (page - 1) * size;
        return eventRepository.getAllEvents(page, size);
    }

    @Override
    public Event getEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Event ID  "+ eventId + " not found");
        }
        return event;
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest request) {
        Event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Event ID "+ eventId + " not found for update");
        }
        return eventRepository.updateEventById(eventId, request);
    }

    @Override
    public Event insertEvent(EventRequest request) {
        // Validate venue existence
        Long venueId = request.getVenueId();
        if (venueId != null && venueRepository.getVenueById(venueId) == null) {
            throw new NotFoundException("Venue with ID " + venueId + " not found");
        }

        // Validate attendee existence
        List<Long> attendeeIds = request.getAttendeeIds();
        if (attendeeIds != null && !attendeeIds.isEmpty()) {
            for (Long attendeeId : attendeeIds) {
                if (attendeeRepository.getAttendeeById(attendeeId) == null) {
                    throw new NotFoundException("Attendee with ID " + attendeeId + " not found");
                }
            }
        }

        // Insert the event after validation
        Event event = eventRepository.insertEvent(request);

        // Link attendees to the event
        if (attendeeIds != null && !attendeeIds.isEmpty()) {
            for (Long attendeeId : attendeeIds) {
                eventAttendeeRepository.insertEventIdAndAttendeeId(event.getEventId(), attendeeId);
            }
        }

        // Return the newly created event
        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public void deleteEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Event ID "+ eventId + " not found for deletion");
        }
        eventRepository.deleteEventById(eventId);
    }
}
