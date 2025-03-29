package org.nimol.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.nimol.springhomework003.model.entity.Venue;
import org.nimol.springhomework003.model.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    @Select("""
            SELECT * FROM venues
            ORDER BY venue_id
            OFFSET #{offset} LIMIT #{limit}
            """)
    List<Venue> getAllVenues(@Param("offset") Integer page, @Param("limit") Integer size);

    @ResultMap("venueMapper")
    @Select("""
            SELECT * FROM venues WHERE venue_id = #{venueId}
            """)
    Venue getVenueById(Long venueId);

    @ResultMap("venueMapper")
    @Select("""
            UPDATE venues
            SET venue_name = #{request.venueName}, location = #{request.location}
            WHERE venue_id = #{venueId}
            RETURNING *;
            """)
    Venue updateVenueById(Long venueId, @Param("request") VenueRequest request);

    @ResultMap("venueMapper")
    @Select("""
            INSERT INTO venues 
            VALUES (default, #{request.venueName}, #{request.location})
            RETURNING *;
            """)
    Venue createVenue(@Param("request") VenueRequest request);

    @Delete("""
            DELETE FROM venues WHERE venue_id = #{venueId}
            """)
    void deleteVenueById(Long venueId);
}
