package it.project.service;

import it.project.model.User;
import it.project.repository.UserRepository;

import java.util.List;

public class UserService implements TemplateService<User> {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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


