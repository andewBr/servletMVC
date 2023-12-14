package com.example.service;

import com.example.model.Event;
import com.example.repository.EventRepository;

import java.util.List;

public class EventService  implements TemplateService<Event> {

    private EventRepository eventRepository;

    public EventService() {
        this.eventRepository = new EventRepository();
    }

    @Override
    public Event createEntity(Event event) {
        return eventRepository.create(event);
    }

    @Override
    public Event readEntity(Integer eventId) {
        return eventRepository.read(eventId);
    }

    @Override
    public List<Event> readAllEntity() {
        return eventRepository.readAll();
    }

    @Override
    public Event updateEntity(int eventId, Event event) {
        return eventRepository.update(eventId, event);
    }

    @Override
    public Event deleteEntity(Integer entityId) {
        return eventRepository.delete(entityId);
    }
}
