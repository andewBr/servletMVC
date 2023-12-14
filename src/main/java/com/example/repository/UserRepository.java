package com.example.repository;

import com.example.configurate.HibernateTemplate;
import com.example.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserRepository implements TemplateRepository<User> {

    public User create(User user) {
        return (User) HibernateTemplate.performDatabaseOperation(session -> session.merge(user));
    }

    public User read(Integer userId) {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.get(User.class, userId)
        );
    }

    public List<User> readAll() {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.createQuery("FROM User", User.class).list()
        );
    }

    @Override
    public User update(int userId, User updatedUser) {
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

    public User delete(Integer userId) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
            }
            return user;
        });
    }
}