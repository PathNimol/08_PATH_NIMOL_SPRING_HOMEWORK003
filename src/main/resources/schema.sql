CREATE TABLE IF NOT EXISTS venues
(
    venue_id   SERIAL PRIMARY KEY,
    venue_name VARCHAR(100),
    location   VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS events
(
    event_id   SERIAL PRIMARY KEY,
    event_name VARCHAR(200),
    event_date DATE,
    venue_id   INT NOT NULL,
    FOREIGN KEY (venue_id) REFERENCES venues (venue_id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS attendees
(
    attendee_id SERIAL PRIMARY KEY,
    attendee_name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS event_attendee(
    event_id INT NOT NULL,
    attendee_id INT NOT NULL,
    PRIMARY KEY (event_id, attendee_id),
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (attendee_id) REFERENCES attendees(attendee_id) ON DELETE CASCADE ON UPDATE CASCADE
)