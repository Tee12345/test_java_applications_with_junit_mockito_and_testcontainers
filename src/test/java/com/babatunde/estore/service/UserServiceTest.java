package com.babatunde.estore.service;

import com.babatunde.estore.data.UsersRepository;
import com.babatunde.estore.model.User;
import com.babatunde.estore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UsersRepository usersRepository;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void init() {
         firstName = "John";
         lastName = "Mark";
         email = "john@gmail.com";
         password = "12345678";
         repeatPassword = "12345678";
    }

    @DisplayName("User Object Created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnUserObject() {
        //Arrange
        Mockito.when(usersRepository.save(Mockito.any(User.class))).thenReturn(true);

        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //Assert
        assertNotNull(user, "The createUser() should not have returned null");
        assertEquals(firstName, user.getFirstName(), "UseName firstName is incorrect");
        assertEquals(lastName, user.getLastName(), "UseName lastName is incorrect");
        assertEquals(email, user.getEmail(), "User's Email is incorrect");
        assertNotNull(user.getId(), "User id id missing");
    }

    @DisplayName("Empty firstName causes correct exception")
    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {
        //Arrange
        String firstName ="";
        String expectedExceptionMessage = "User's firstName is empty";

        //Act & Assert
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user = userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Empty firstName should have caused Illegal Argument Exception");

        //Assert
        assertEquals(expectedExceptionMessage, thrown.getMessage(),
                "Exception error message is not correct");
    }
}