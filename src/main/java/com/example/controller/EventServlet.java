import com.example.model.Event;
import com.example.model.File;
import com.example.model.User;
import com.example.repository.FileRepository;
import com.example.service.EventService;
import com.example.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventServlet extends HttpServlet {

    private final EventService eventService;
    private final UserService userService;
    private final FileRepository fileRepository;


    public EventServlet() {
        this.eventService = new EventService();
        this.userService = new UserService();
        this.fileRepository = new FileRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String eventIdParam = req.getParameter("id");

        if (eventIdParam != null) {
            // If eventId is provided, retrieve a specific event
            int eventId = Integer.parseInt(eventIdParam);
            Event event = eventService.readEntity(eventId);

            if (event != null) {
                resp.getWriter().write(event.toString());
            } else {
                resp.getWriter().write("Event not found.");
            }
        } else {
            // If no eventId is provided, retrieve all events
            List<Event> events = eventService.readAllEntity();
            resp.getWriter().write(events.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Handle POST request to create a new event
        String userIdParam = req.getParameter("userId");
        String fileIdParam = req.getParameter("fileId");

        if (userIdParam != null && fileIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            int fileId = Integer.parseInt(fileIdParam);

            User user = userService.readEntity(userId); // Assuming UserService has a method to get a user by ID
            File file = fileRepository.readFile(fileId); // Assuming FileService has a method to get a file by ID

            if (user != null && file != null) {
                Event newEvent = new Event(null, user, file);
                Event createdEvent = eventService.createEntity(newEvent);
                resp.getWriter().write("Event created with id: " + createdEvent.getId());
            } else {
                resp.getWriter().write("Invalid user ID or file ID.");
            }
        } else {
            resp.getWriter().write("Invalid input. Both user ID and file ID are required.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Handle PUT request to update an existing event
        String eventIdParam = req.getParameter("id");
        String userIdParam = req.getParameter("userId");
        String fileIdParam = req.getParameter("fileId");

        if (eventIdParam != null && userIdParam != null && fileIdParam != null) {
            int eventId = Integer.parseInt(eventIdParam);
            int userId = Integer.parseInt(userIdParam);
            int fileId = Integer.parseInt(fileIdParam);

            User user = userService.readEntity(userId);
            File file = fileRepository.readFile(fileId);

            if (user != null && file != null) {
                Event existingEvent = eventService.readEntity(eventId);
                if (existingEvent != null) {
                    existingEvent.setUser(user);
                    existingEvent.setFile(file);

                    Event updatedEvent = eventService.updateEntity(eventId, existingEvent);
                    resp.getWriter().write("Event updated successfully.");
                } else {
                    resp.getWriter().write("Event not found.");
                }
            } else {
                resp.getWriter().write("Invalid user ID or file ID.");
            }
        } else {
            resp.getWriter().write("Invalid input. Event ID, user ID, and file ID are required for update.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Handle DELETE request to delete an existing event
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
