package it.project.service;

import it.project.model.FileModel;
import it.project.repository.FileRepository;

import java.util.List;

public class FileService implements TemplateService<FileModel> {

    private FileRepository fileRepository;

    public FileService() {
        this.fileRepository = new FileRepository();
    }

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileModel createEntity(FileModel fileModel) {
        return fileRepository.create(fileModel);
    }

    @Override
    public FileModel readEntity(Integer entityId) {
        return fileRepository.read(entityId);
    }

    @Override
    public List<FileModel> readAllEntity() {
        return fileRepository.readAll();
    }

    @Override
    public FileModel updateEntity(int fileId, FileModel fileModel) {
        return fileRepository.update(fileId, fileModel);
    }

    @Override
    public FileModel deleteEntity(Integer entityId) {
        return null;
    }
}
