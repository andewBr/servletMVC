package it.project.service;

import it.project.model.Event;
import it.project.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class EventServiceTest {

    private EventRepository eventRepository;
    private EventService eventService;

    @BeforeEach
    void setUp() {
        eventRepository = mock(EventRepository.class);
        eventService = new EventService(eventRepository);
    }

    @Test
    void testCreateEntity() {
        Event eventToCreate = new Event();
        when(eventRepository.create(eventToCreate)).thenReturn(eventToCreate);

        Event createdEvent = eventService.createEntity(eventToCreate);

        assertEquals(eventToCreate, createdEvent);
        verify(eventRepository, times(1)).create(eventToCreate);
    }

    @Test
    void testReadEntity() {
        int eventId = 1;
        Event expectedEvent = new Event();
        when(eventRepository.read(eventId)).thenReturn(expectedEvent);

        Event retrievedEvent = eventService.readEntity(eventId);

        assertEquals(expectedEvent, retrievedEvent);
        verify(eventRepository, times(1)).read(eventId);
    }

    @Test
    void testReadAllEntity() {
        List<Event> expectedEvents = Arrays.asList(
                new Event(),
                new Event()
        );
        when(eventRepository.readAll()).thenReturn(expectedEvents);

        List<Event> retrievedEvents = eventService.readAllEntity();

        assertEquals(expectedEvents, retrievedEvents);
        verify(eventRepository, times(1)).readAll();
    }

    @Test
    void testUpdateEntity() {
        int eventId = 1;
        Event updatedEvent = new Event();
        when(eventRepository.update(eventId, updatedEvent)).thenReturn(updatedEvent);

        Event result = eventService.updateEntity(eventId, updatedEvent);

        assertEquals(updatedEvent, result);
        verify(eventRepository, times(1)).update(eventId, updatedEvent);
    }

    @Test
    void testDeleteEntity() {
        int eventId = 1;
        Event deletedEvent = new Event();
        when(eventRepository.delete(eventId)).thenReturn(deletedEvent);

        Event result = eventService.deleteEntity(eventId);

        assertEquals(deletedEvent, result);
        verify(eventRepository, times(1)).delete(eventId);
    }
}