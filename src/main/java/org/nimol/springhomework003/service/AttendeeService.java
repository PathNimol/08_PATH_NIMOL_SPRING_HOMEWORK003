package org.nimol.springhomework003.service;

import org.nimol.springhomework003.model.entity.Attendee;
import org.nimol.springhomework003.model.request.AttendeeRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendeeService {
    
    List<Attendee> getAllAttendees(Integer page, Integer size);

    Attendee getAttendeeById(Long attendeeId);

    Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request);

    Attendee createAttendee(AttendeeRequest request);

    void deleteAttendeeById(Long attendeeId);
}
