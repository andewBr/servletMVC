package it.project.service;

import it.project.model.User;
import it.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testCreateEntity() {
        User userToCreate = new User();
        when(userRepository.create(userToCreate)).thenReturn(userToCreate);

        User createdUser = userService.createEntity(userToCreate);

        assertEquals(userToCreate, createdUser);
        verify(userRepository, times(1)).create(userToCreate);
    }

    @Test
    void testReadEntity() {
        int userId = 1;
        User expectedUser = new User();
        when(userRepository.read(userId)).thenReturn(expectedUser);

        User retrievedUser = userService.readEntity(userId);

        assertEquals(expectedUser, retrievedUser);
        verify(userRepository, times(1)).read(userId);
    }

    @Test
    void testReadAllEntity() {
        List<User> expectedUsers = Arrays.asList(
                new User(),
                new User()
        );
        when(userRepository.readAll()).thenReturn(expectedUsers);

        List<User> retrievedUsers = userService.readAllEntity();

        assertEquals(expectedUsers, retrievedUsers);
        verify(userRepository, times(1)).readAll();
    }

    @Test
    void testUpdateEntity() {
        int userId = 1;
        User updatedUser = new User();
        when(userRepository.update(userId, updatedUser)).thenReturn(updatedUser);

        User result = userService.updateEntity(userId, updatedUser);

        assertEquals(updatedUser, result);
        verify(userRepository, times(1)).update(userId, updatedUser);
    }

    @Test
    void testDeleteEntity() {
        int userId = 1;
        User deletedUser = new User();
        when(userRepository.delete(userId)).thenReturn(deletedUser);

        User result = userService.deleteEntity(userId);

        assertEquals(deletedUser, result);
        verify(userRepository, times(1)).delete(userId);
    }
}
