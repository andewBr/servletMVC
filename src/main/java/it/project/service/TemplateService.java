package it.project.service;

import java.util.List;

public interface TemplateService<T> {
    T createEntity(T entity);

    T readEntity(Integer entityId);

    List<T> readAllEntity() ;

    T updateEntity(int entityId, T entity);

    T deleteEntity(Integer entityId);
}


