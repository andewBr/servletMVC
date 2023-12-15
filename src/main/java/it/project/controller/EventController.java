package it.project.controller;

import it.project.model.Event;
import it.project.service.EventService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventController extends HttpServlet {

    private final EventService eventService;


    public EventController() {
        this.eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String eventIdParam = req.getParameter("id");

        if (eventIdParam != null) {
            int eventId = Integer.parseInt(eventIdParam);
            Event event = eventService.readEntity(eventId);

            if (event != null) {
                resp.getWriter().write(event.toString());
            }
        } else {
            List<Event> events = eventService.readAllEntity();
            resp.getWriter().write(events.toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String eventIdParam = req.getParameter("id");

        if (eventIdParam != null) {
            int eventId = Integer.parseInt(eventIdParam);
            eventService.deleteEntity(eventId);
            resp.getWriter().write("Event deleted successfully.");
        } else {
            resp.getWriter().write("Invalid input. Event ID is required for deletion.");
        }
    }
}
