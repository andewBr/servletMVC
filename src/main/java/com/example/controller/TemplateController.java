package com.example.controller;

public interface TemplateController {

    String getUserById(String userId);

    String getAllUsers();

    String createUser(String userName);

    String updateUser(String userId, String userName);

    String deleteUser(String userId);
}
