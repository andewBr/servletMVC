package com.example.repository;

import com.example.configurate.HibernateTemplate;
import com.example.model.Event;

import java.util.List;

public interface TemplateRepository<T> {
    T create(T event);

    T read(Integer eventId);

    List<T> readAll();

    T update(int eventId, T updatedEntity);

    T delete(Integer eventId);
}
