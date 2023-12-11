package com.example.controller;

import com.example.model.File;
import com.example.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FileController extends HttpServlet {
    private final FileService fileService;

    public FileController() {
        this.fileService = new FileService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileIdParam = req.getParameter("id");

        if (fileIdParam != null) {
            int fileId = Integer.parseInt(fileIdParam);
            File file = fileService.readEntity(fileId);
            if (file != null) {
                resp.getWriter().write(file.toString());
            } else {
                resp.getWriter().write("File not found.");
            }
        } else {
            List<File> files = fileService.readAllEntity();
            resp.getWriter().write(files.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("name");
        String filePath = req.getParameter("filePath");

        if (fileName != null && !fileName.isEmpty() && filePath != null && !filePath.isEmpty()) {
            File newFile = new File();
            newFile.setName(fileName);
            newFile.setFilePath(filePath);
            File createdFile = fileService.createEntity(newFile);
            resp.getWriter().write("File created with id: " + createdFile.getId());
        } else {
            resp.getWriter().write("Invalid input. Both name and filePath are required.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileIdParam = req.getParameter("id");
        String fileName = req.getParameter("name");
        String filePath = req.getParameter("filePath");

        if (fileIdParam != null && fileName != null && !fileName.isEmpty() && filePath != null && !filePath.isEmpty()) {
            int fileId = Integer.parseInt(fileIdParam);
            File updatedFile = new File();
            updatedFile.setId(fileId);
            updatedFile.setName(fileName);
            updatedFile.setFilePath(filePath);

            File result = fileService.updateEntity(fileId, updatedFile);
            if (result != null) {
                resp.getWriter().write("File updated successfully.");
            } else {
                resp.getWriter().write("File not found.");
            }
        } else {
            resp.getWriter().write("Invalid input. Both id, name, and filePath are required for update.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileIdParam = req.getParameter("id");

        if (fileIdParam != null) {
            int fileId = Integer.parseInt(fileIdParam);
            fileService.deleteEntity(fileId);
            resp.getWriter().write("File deleted successfully.");
        } else {
            resp.getWriter().write("Invalid input. File id is required for deletion.");
        }
    }
}

