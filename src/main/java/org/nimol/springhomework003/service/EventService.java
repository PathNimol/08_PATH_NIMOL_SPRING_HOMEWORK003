package org.nimol.springhomework003.service;

import org.nimol.springhomework003.model.entity.Event;
import org.nimol.springhomework003.model.request.EventRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size);
    Event getEventById(Long eventId);
    Event updateEventById(Long eventId, EventRequest request);
    Event insertEvent(EventRequest request);
    void deleteEventById(Long eventId);
}
