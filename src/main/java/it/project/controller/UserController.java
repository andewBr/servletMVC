package it.project.controller;

import it.project.configurate.HttpServletHelper;
import it.project.dto.FileDTO;
import it.project.dto.UserDTO;
import it.project.model.User;
import it.project.service.UserService;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UserController extends HttpServlet {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("id");

        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            User user = userService.readEntity(userId);
            if (user != null) {
                resp.getWriter().write(user.toString());
            } else {
                resp.getWriter().write("User not found.");
            }
        } else {
            List<User> users = userService.readAllEntity();
            resp.getWriter().write(users.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDTO fileDTO = HttpServletHelper.convertStringIntoObject(req, UserDTO.class);

        User newUser = new User();
        newUser.setName(fileDTO.getName());
        User createdUser = userService.createEntity(newUser);
        resp.getWriter().write("User created: " + createdUser);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO fileDTO = HttpServletHelper.convertStringIntoObject(req, UserDTO.class);

        User newUserDTO = new User();
        newUserDTO.setId(fileDTO.getId());
        newUserDTO.setName(fileDTO.getName());

        User updatedUser = new User();
        updatedUser.setId(newUserDTO.getId());
        updatedUser.setName(newUserDTO.getName());


        User result = userService.updateEntity(updatedUser.getId(), updatedUser);
        if (result != null) {
            resp.getWriter().write("User updated successfully.");
        } else {
            resp.getWriter().write("User not found.");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO fileDTO = HttpServletHelper.convertStringIntoObject(req, UserDTO.class);
            userService.deleteEntity(fileDTO.getId());
            resp.getWriter().write("User deleted successfully: " + fileDTO );
    }
}
