package it.project.repository;

import it.project.configurate.HibernateTemplate;
import it.project.model.FileModel;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class FileRepository implements TemplateRepository<FileModel> {

    public FileModel create(FileModel fileModel) {
        return (FileModel) HibernateTemplate.performDatabaseOperation(session -> session.merge(fileModel));
    }

    public FileModel read(Integer fileId) {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.get(FileModel.class, fileId)
        );
    }

    public List<FileModel> readAll() {
        return HibernateTemplate.performDatabaseOperation(session ->
                session.createQuery("FROM File", FileModel.class).list()
        );
    }

    @Override
    public FileModel update(int fileId, FileModel updatedFileModel) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            FileModel existingFileModel = session.get(FileModel.class, fileId);
            if (existingFileModel != null) {
                existingFileModel.setName(updatedFileModel.getName());
                existingFileModel.setFilePath(updatedFileModel.getFilePath());
                session.update(existingFileModel);
            }
            return existingFileModel;
        });
    }

    public FileModel delete(Integer fileId) {
        return HibernateTemplate.performDatabaseOperation(session -> {
            FileModel fileModel = session.get(FileModel.class, fileId);
            if (fileModel != null) {
                session.delete(fileModel);
            }
            return fileModel;
        });
    }

}