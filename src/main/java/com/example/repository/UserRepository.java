package com.example.repository;

import com.example.configurate.HibernateTemplate;
import com.example.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserRepository {

    public User createUser(User user) {
        HibernateTemplate.performDatabaseOperation(session -> {
            session.save(user);
            return null;
        });
        return user;
    }

    public User readUser(Integer userId) {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.get(User.class, userId)
        );
    }

    public List<User> readAllUsers() {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.createQuery("FROM User", User.class).list()
        );
    }

    public User updateUser(Integer userId, User updatedUser) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            User existingUser = session.get(User.class, userId);
            if (existingUser != null) {
                existingUser.setName(updatedUser.getName());
                existingUser.setEvents(updatedUser.getEvents());
                session.update(existingUser);
            }
            return existingUser;
        });
    }

    public User deleteUser(Integer userId) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
            }
            return user;
        });
    }
}