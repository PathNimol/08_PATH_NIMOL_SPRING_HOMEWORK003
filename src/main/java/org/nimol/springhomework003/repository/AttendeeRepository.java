package org.nimol.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.nimol.springhomework003.model.entity.Attendee;
import org.nimol.springhomework003.model.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    @Select("""
            SELECT * FROM attendees
            ORDER BY attendee_id
            OFFSET #{offset} LIMIT #{limit}
            """)
    List<Attendee> getAllAttendees(@Param("offset") Integer page, @Param("limit") Integer size);

    @ResultMap("attendeeMapper")
    @Select("""
            SELECT * FROM attendees WHERE attendee_id = #{attendeeId}
            """)
    Attendee getAttendeeById(Long attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
           UPDATE attendees
           SET attendee_name = #{req.attendeeName}, email = #{req.email}
           WHERE attendee_id = #{attendeeId}
           RETURNING *;
           """)
    Attendee updateAttendeeById(Long attendeeId, @Param("req") AttendeeRequest request);

    @ResultMap("attendeeMapper")
    @Select("""
            INSERT INTO attendees VALUES (default, #{req.attendeeName}, #{req.email})
            RETURNING *;
            """)
    Attendee createAttendee(@Param("req") AttendeeRequest request);

    @Delete("""
            DELETE FROM attendees WHERE attendee_id = #{attendeeId}
            """)
    void deleteAttendeeById(Long attendeeId);
}
