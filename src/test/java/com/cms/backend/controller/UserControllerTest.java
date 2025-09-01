package com.cms.backend.controller;

import com.cms.backend.controller.user.UserController;
import com.cms.backend.exception.UserNotFoundException;
import com.cms.backend.model.User;
import com.cms.backend.model.enums.UserRole;
import com.cms.backend.model.enums.UserStatus;
import com.cms.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User(1L, "Alice", "alice@example.com", UserRole.ADMIN, UserStatus.ACTIVE);
        user2 = new User(2L, "Bob", "bob@example.com", UserRole.EDITOR, UserStatus.INACTIVE);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserById_Found() {
        when(userService.getUserById(1L)).thenReturn(user1);

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Alice", response.getBody().getName());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userService.getUserById(3L)).thenThrow(new UserNotFoundException("User with id 3 not found"));

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userController.getUserById(3L));

        assertEquals("User with id 3 not found", exception.getMessage());
        verify(userService, times(1)).getUserById(3L);
    }

    @Test
    void testCreateUser() {
        when(userService.createUser(user1)).thenReturn(user1);

        ResponseEntity<User> response = userController.createUser(user1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user1, response.getBody());
        verify(userService, times(1)).createUser(user1);
    }

    @Test
    void testUpdateUser_Success() {
        doNothing().when(userService).updateUser(1L, user1);

        ResponseEntity<Void> response = userController.updateUser(1L, user1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).updateUser(1L, user1);
    }

    @Test
    void testUpdateUser_NotFound() {
        doThrow(new UserNotFoundException("User with id 3 not found"))
                .when(userService).updateUser(3L, user1);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userController.updateUser(3L, user1));

        assertEquals("User with id 3 not found", exception.getMessage());
        verify(userService, times(1)).updateUser(3L, user1);
    }

    @Test
    void testDeleteUser_Success() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    void testDeleteUser_NotFound() {
        doThrow(new UserNotFoundException("User with id 3 not found"))
                .when(userService).deleteUser(3L);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userController.deleteUser(3L));

        assertEquals("User with id 3 not found", exception.getMessage());
        verify(userService, times(1)).deleteUser(3L);
    }
}
