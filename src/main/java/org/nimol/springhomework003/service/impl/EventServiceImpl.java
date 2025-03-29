package org.nimol.springhomework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.nimol.springhomework003.exception.NotFoundException;
import org.nimol.springhomework003.model.entity.Event;
import org.nimol.springhomework003.model.request.EventRequest;
import org.nimol.springhomework003.repository.EventAttendeeRepository;
import org.nimol.springhomework003.repository.EventRepository;
import org.nimol.springhomework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        page = (page - 1) * size;
        return eventRepository.getAllEvents(page, size);
    }

    @Override
    public Event getEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Event not found");
        }
        return event;
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest request) {
        Event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Event not found for update");
        }
        return eventRepository.updateEventById(eventId, request);
    }

    @Override
    public Event insertEvent(EventRequest request) {
        Event event = eventRepository.insertEvent(request);
        for(Long attendeeId: request.getAttendeeIds()){
            eventAttendeeRepository.insertEventIdAndAttendeeId(event.getEventId(), attendeeId);
        }
        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public void deleteEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new NotFoundException("Event not found for deletion");
        }
        eventRepository.deleteEventById(eventId);
    }
}
