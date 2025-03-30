package org.nimol.springhomework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.nimol.springhomework003.exception.NotFoundException;
import org.nimol.springhomework003.model.entity.Attendee;
import org.nimol.springhomework003.model.request.AttendeeRequest;
import org.nimol.springhomework003.repository.AttendeeRepository;
import org.nimol.springhomework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {
        page = (page - 1) * size;
        return attendeeRepository.getAllAttendees(page, size);
    }

    @Override
    public Attendee getAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if(attendee == null) {
            throw new NotFoundException("Attendee ID "+ attendeeId +" not found");
        }
        return attendee;
    }

    @Override
    public Attendee updateAttendeeById(Long attendeeId, AttendeeRequest request) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if(attendee == null) {
            throw new NotFoundException("Attendee ID "+ attendeeId +" not found for update");
        }
        return attendeeRepository.updateAttendeeById(attendeeId, request);
    }

    @Override
    public Attendee createAttendee(AttendeeRequest request) {
        return attendeeRepository.createAttendee(request);
    }

    @Override
    public void deleteAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if(attendee == null) {
            throw new NotFoundException("Attendee ID "+ attendeeId + " not found for deletion");
        }
        attendeeRepository.deleteAttendeeById(attendeeId);
    }
}
