package org.nimol.springhomework003.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.nimol.springhomework003.model.entity.Attendee;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {

    @Result(property = "attendeeId", column = "attendee_id")
    @Result(property = "attendeeName", column = "attendee_name")
    @Select("""
            SELECT * FROM event_attendee ea
            INNER JOIN attendees a
            ON ea.attendee_id = a.attendee_id
            WHERE event_id = #{eventId}
            """)
    List<Attendee> getAllAttendeesByEventId(Long eventId);

    @Insert("""
            INSERT INTO event_attendee VALUES (#{eventId}, #{attendeeId})
            """)
    void insertEventIdAndAttendeeId(Long eventId, Long attendeeId);
}