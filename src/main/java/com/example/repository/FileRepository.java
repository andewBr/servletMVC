package com.example.repository;

import com.example.configurate.HibernateTemplate;
import com.example.model.File;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class FileRepository {

    public File createFile(File file) {
        HibernateTemplate.performDatabaseOperation(session -> {
            session.save(file);
            return null;
        });
        return file;
    }

    public File readFile(Integer fileId) {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.get(File.class, fileId)
        );
    }

    public List<File> readAllFiles() {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.createQuery("FROM File", File.class).list()
        );
    }

    public File updateFile(Integer fileId, File updatedFile) {
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

    public void deleteFile(Integer fileId) {
        HibernateTemplate.performDatabaseOperation(session -> {
            File file = session.get(File.class, fileId);
            if (file != null) {
                session.delete(file);
            }
            return null;
        });
    }
}