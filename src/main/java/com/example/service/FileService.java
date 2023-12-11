package com.example.service;

import com.example.model.Event;
import com.example.model.File;
import com.example.repository.FileRepository;

import java.util.List;

public class FileService implements TemplateService<File> {

    private FileRepository fileRepository;


    @Override
    public File createEntity(File file) {
        return fileRepository.createFile(file);
    }

    @Override
    public File readEntity(Integer entityId) {
        return fileRepository.readFile(entityId);
    }

    @Override
    public List<File> readAllEntity() {
        return fileRepository.readAllFiles();
    }

    @Override
    public File updateEntity(int fileId, File file) {
        return fileRepository.updateFile(fileId, file);
    }

    @Override
    public File deleteEntity(Integer entityId) {
        return null;
    }
}
