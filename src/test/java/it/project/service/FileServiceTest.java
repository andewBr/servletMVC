package it.project.service;
import it.project.model.FileModel;
import it.project.repository.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FileServiceTest {

    private FileRepository fileRepository;
    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileRepository = mock(FileRepository.class);
        fileService = new FileService(fileRepository);
    }

    @Test
    void testCreateEntity() {
        FileModel fileToCreate = new FileModel();
        when(fileRepository.create(fileToCreate)).thenReturn(fileToCreate);

        FileModel createdFile = fileService.createEntity(fileToCreate);

        assertEquals(fileToCreate, createdFile);
        verify(fileRepository, times(1)).create(fileToCreate);
    }

    @Test
    void testReadEntity() {
        int fileId = 1;
        FileModel expectedFile = new FileModel();
        when(fileRepository.read(fileId)).thenReturn(expectedFile);

        FileModel retrievedFile = fileService.readEntity(fileId);

        assertEquals(expectedFile, retrievedFile);
        verify(fileRepository, times(1)).read(fileId);
    }

    @Test
    void testReadAllEntity() {
        List<FileModel> expectedFiles = Arrays.asList(
                new FileModel(),
                new FileModel()
        );
        when(fileRepository.readAll()).thenReturn(expectedFiles);

        List<FileModel> retrievedFiles = fileService.readAllEntity();

        assertEquals(expectedFiles, retrievedFiles);
        verify(fileRepository, times(1)).readAll();
    }

    @Test
    void testUpdateEntity() {
        int fileId = 1;
        FileModel updatedFile = new FileModel();
        when(fileRepository.update(fileId, updatedFile)).thenReturn(updatedFile);

        FileModel result = fileService.updateEntity(fileId, updatedFile);

        assertEquals(updatedFile, result);
        verify(fileRepository, times(1)).update(fileId, updatedFile);
    }

    @Test
    void testDeleteEntity() {
        int fileId = 1;
        FileModel deletedFile = new FileModel();
        when(fileRepository.delete(fileId)).thenReturn(deletedFile);

        FileModel result = fileService.deleteEntity(fileId);

        assertEquals(deletedFile, result);
        verify(fileRepository, times(1)).delete(fileId);
    }
}
