package com.example.repository;


import com.example.configurate.HibernateTemplate;
import com.example.model.Event;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class EventRepository {

    public Event createEvent(Event event) {
        HibernateTemplate.performDatabaseOperation(session -> {
            session.save(event);
            return null;
        });
        return event;
    }

    public Event readEvent(Integer eventId) {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.get(Event.class, eventId)
        );
    }

    public List<Event> readAllEvents() {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.createQuery("FROM Event", Event.class).list()
        );
    }

    public Event updateEvent(int eventId, Event updatedEvent) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            Event existingEvent = session.get(Event.class, eventId);
            if (existingEvent != null) {
                existingEvent.setUser(updatedEvent.getUser());
                existingEvent.setFile(updatedEvent.getFile());
                session.update(existingEvent);
            }
            return existingEvent;
        });
    }

    public Event deleteEvent(Integer eventId) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            Event event = session.get(Event.class, eventId);
            if (event != null) {
                session.delete(event);
            }
            return event;
        });
    }
}