package org.nimol.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.nimol.springhomework003.model.entity.Event;
import org.nimol.springhomework003.model.request.EventRequest;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.nimol.springhomework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "org.nimol.springhomework003.repository.EventAttendeeRepository.getAllAttendeesByEventId"))
    })
    @Select("""
            SELECT * FROM events
            ORDER BY event_id
            OFFSET #{offset} LIMIT #{limit}
            """)
    List<Event> getAllEvents(@Param("offset") Integer page, @Param("limit") Integer size);

    @ResultMap("eventMapper")
    @Select("""
            SELECT * FROM events
            WHERE event_id = #{eventId}
            """)
    Event getEventById(@Param("eventId") Long eventId);

    @ResultMap("eventMapper")
    @Select("""
            UPDATE events
            SET event_name = #{req.eventName}, event_date = #{req.eventDate}, venue_id = #{req.venueId}
            WHERE event_id = #{eventId}
            RETURNING *;
            """)
    Event updateEventById(@Param("eventId") Long eventId, @Param("req") EventRequest request);

    @ResultMap("eventMapper")
    @Select("""
            INSERT INTO events VALUES (default, #{req.eventName}, #{req.eventDate}, #{req.venueId})
            RETURNING *;
            """)
    Event insertEvent(@Param("req") EventRequest request);

    @Delete("""
            DELETE FROM events WHERE event_id = #{eventId}
            """)
    void deleteEventById(@Param("eventId") Long eventId);
}
