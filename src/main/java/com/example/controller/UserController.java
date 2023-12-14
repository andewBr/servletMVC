package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name");
        if (userName != null && !userName.isEmpty()) {
            User newUser = new User();
            newUser.setName(userName);
            User createdUser = userService.createEntity(newUser);
            resp.getWriter().write("User created with id: " + createdUser.getId());
        } else {
            resp.getWriter().write("Invalid input. Name is required.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("id");
        String userName = req.getParameter("name");

        if (userIdParam != null && userName != null && !userName.isEmpty()) {
            int userId = Integer.parseInt(userIdParam);
            User updatedUser = new User();
            updatedUser.setId(userId);
            updatedUser.setName(userName);


            User result = userService.updateEntity(userId, updatedUser);
            if (result != null) {
                resp.getWriter().write("User updated successfully.");
            } else {
                resp.getWriter().write("User not found.");
            }
        } else {
            resp.getWriter().write("Invalid input. Both id and name are required for update.");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("id");

        if (userIdParam != null) {
            int userId = Integer.parseInt(userIdParam);
            userService.deleteEntity(userId);
            resp.getWriter().write("User deleted successfully.");
        } else {
            resp.getWriter().write("Invalid input. User id is required for deletion.");
        }
    }
}
