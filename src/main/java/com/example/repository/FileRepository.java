package com.example.repository;

import com.example.configurate.HibernateTemplate;
import com.example.model.File;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class FileRepository implements TemplateRepository<File> {

    public File create(File file) {
        return (File) HibernateTemplate.performDatabaseOperation(session -> session.merge(file));
    }

    public File read(Integer fileId) {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.get(File.class, fileId)
        );
    }

    public List<File> readAll() {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.createQuery("FROM File", File.class).list()
        );
    }

    @Override
    public File update(int fileId, File updatedFile) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            File existingFile = session.get(File.class, fileId);
            if (existingFile != null) {
                existingFile.setName(updatedFile.getName());
                existingFile.setFilePath(updatedFile.getFilePath());
                session.update(existingFile);
            }
            return existingFile;
        });
    }

    public File delete(Integer fileId) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            File file = session.get(File.class, fileId);
            if (file != null) {
                session.delete(file);
            }
            return file;
        });
    }

}