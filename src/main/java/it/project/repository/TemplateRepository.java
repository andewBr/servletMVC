package it.project.repository;

import java.util.List;

public interface TemplateRepository<T> {
    T create(T event);

    T read(Integer eventId);

    List<T> readAll();

    T update(int eventId, T updatedEntity);

    T delete(Integer eventId);
}
