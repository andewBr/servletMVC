package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;

import java.util.List;

public class UserService implements TemplateService<User> {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    @Override
    public User createEntity(User user) {
        return userRepository.create(user);
    }

    @Override
    public User readEntity(Integer userId) {
        return userRepository.read(userId);
    }

    @Override
    public List<User> readAllEntity() {
        return userRepository.readAll();
    }

    @Override
    public User updateEntity(int userId, User updatedUser) {
        return userRepository.update(userId, updatedUser);
    }

    @Override
    public User deleteEntity(Integer userId) {
        return userRepository.delete(userId);
    }
}


