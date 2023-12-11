package com.example.service;

import com.example.model.Event;
import com.example.model.User;
import com.example.repository.EventRepository;

import java.util.List;

public class EventService  implements TemplateService<Event> {

    private EventRepository eventRepository;

    public EventService() {
        this.eventRepository = new EventRepository();
    }

    @Override
    public Event createEntity(Event event) {
        return eventRepository.createEvent(event);
    }

    @Override
    public Event readEntity(Integer eventId) {
        return eventRepository.readEvent(eventId);
    }

    @Override
    public List<Event> readAllEntity() {
        return eventRepository.readAllEvents();
    }

    @Override
    public Event updateEntity(int eventId, Event event) {
        return eventRepository.updateEvent(eventId, event);
    }

    @Override
    public Event deleteEntity(Integer entityId) {
        return eventRepository.deleteEvent(entityId);
    }
}
