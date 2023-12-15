package it.project.controller;

import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;


public class FileController2 extends HttpServlet {

    String UPLOAD_DIRECTORY = "B:\\PROGRAMMING\\CODE\\javaProjects\\itvdn\\ITVDN_project_lesson\\src\\main\\resources\\files\\";

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

            for (FileItem item : fileItems) {
                String fileName = new File(item.getName()).getName();
                System.out.println("fileName: " + fileName);
                String userName = request.getHeader("User-Name");

                System.out.println("User-Name: " + userName);
                System.out.println("Field Name: " + item.getFieldName());

                // Save the file
                File uploadedFile = new File(UPLOAD_DIRECTORY + fileName);
                try (InputStream fileContent = item.getInputStream()) {
                    Files.copy(fileContent, uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                response.getWriter().write("File " + fileName + " has been uploaded successfully!");
            }
    }

}

