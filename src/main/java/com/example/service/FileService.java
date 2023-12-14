package com.example.service;

import com.example.model.File;
import com.example.repository.FileRepository;

import java.util.List;

public class FileService implements TemplateService<File> {

    private FileRepository fileRepository;

    public FileService() {
        this.fileRepository = new FileRepository();
    }

    @Override
    public File createEntity(File file) {
        return fileRepository.create(file);
    }

    @Override
    public File readEntity(Integer entityId) {
        return fileRepository.read(entityId);
    }

    @Override
    public List<File> readAllEntity() {
        return fileRepository.readAll();
    }

    @Override
    public File updateEntity(int fileId, File file) {
        return fileRepository.update(fileId, file);
    }

    @Override
    public File deleteEntity(Integer entityId) {
        return null;
    }
}
