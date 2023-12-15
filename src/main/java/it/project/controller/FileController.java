package it.project.controller;

import it.project.configurate.HttpServletHelper;

import it.project.dto.EventRequestDTO;
import it.project.dto.FileDTO;
import it.project.model.Event;
import it.project.model.FileModel;
import it.project.model.User;
import it.project.repository.FileRepository;
import it.project.service.EventService;
import it.project.service.FileService;
import it.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FileController extends HttpServlet {

    private final EventService eventService;
    private final UserService userService;
    private final FileRepository fileRepository;
    private final FileService fileService;


    public FileController() {
        this.eventService = new EventService();
        this.userService = new UserService();
        this.fileRepository = new FileRepository();
        this.fileService = new FileService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileIdParam = req.getParameter("id");

        if (fileIdParam != null) {
            int fileId = Integer.parseInt(fileIdParam);
            FileModel fileModel = fileService.readEntity(fileId);
            if (fileModel != null) {
                resp.getWriter().write(fileModel.toString());
            } else {
                resp.getWriter().write("FileModel not found.");
            }
        } else {
            List<FileModel> fileModels = fileService.readAllEntity();
            resp.getWriter().write(fileModels.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventRequestDTO eventRequestDTO = HttpServletHelper.convertStringIntoObject(req, EventRequestDTO.class);

        User user = userService.readEntity(eventRequestDTO.getUser().getId());
        FileModel fileModel = fileRepository.read(eventRequestDTO.getFile().getId());

        Event newEvent = new Event(user, fileModel);
        Event createdEvent = eventService.createEntity(newEvent);
        resp.getWriter().write("created event: " + createdEvent);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileIdParam = req.getParameter("id");
        String fileName = req.getParameter("name");
        String filePath = req.getParameter("filePath");

        if (fileIdParam != null && fileName != null && !fileName.isEmpty() && filePath != null && !filePath.isEmpty()) {
            int fileId = Integer.parseInt(fileIdParam);
            FileModel updatedFileModel = new FileModel();
            updatedFileModel.setId(fileId);
            updatedFileModel.setName(fileName);
            updatedFileModel.setFilePath(filePath);

            FileModel result = fileService.updateEntity(fileId, updatedFileModel);
            if (result != null) {
                resp.getWriter().write("FileModel updated successfully.");
            } else {
                resp.getWriter().write("FileModel not found.");
            }
        } else {
            resp.getWriter().write("Invalid input. Both id, name, and filePath are required for update.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            FileDTO fileDTO = HttpServletHelper.convertStringIntoObject(req, FileDTO.class);

            fileService.deleteEntity(fileDTO.getId());
            resp.getWriter().write("FileModel deleted successfully: " + fileDTO);
    }
}

