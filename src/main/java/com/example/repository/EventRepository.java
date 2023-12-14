package com.example.repository;


import com.example.configurate.HibernateTemplate;
import com.example.model.Event;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class EventRepository implements TemplateRepository<Event> {

    public Event create(Event event) {
        return (Event) HibernateTemplate.performDatabaseOperation(session -> session.merge(event));
    }

    public Event read(Integer eventId) {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.get(Event.class, eventId)
        );
    }

    public List<Event> readAll() {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.createQuery("FROM Event", Event.class).list()
        );
    }

    public Event update(int eventId, Event updatedEvent) {
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

    public Event delete(Integer eventId) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            Event event = session.get(Event.class, eventId);
            if (event != null) {
                session.delete(event);
            }
            return event;
        });
    }
}