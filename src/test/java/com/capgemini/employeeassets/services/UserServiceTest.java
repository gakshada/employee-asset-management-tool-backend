package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.User;
import com.capgemini.employeeassets.exception.ExistingUsernameException;
import com.capgemini.employeeassets.exception.InvalidLoginCredentialsException;
import com.capgemini.employeeassets.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setup()
    {
        user =new User();
        user.setUserId(1L);
        user.setUsername("akshada@gmail.com");
        user.setPassword("Akshada@1234");
        user.setUserRole("employee");
    }

    @Test
    public void userLogin()
    {
        // user-precondition or setup. For employee login check
        when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()))
                .thenReturn(Optional.of(user));

        User savedUser = userService.userLogin(user);
        System.out.println(savedUser);
        // then - verify the output
        assertThat(savedUser).isNotNull();

        //Check for admin login
        user.setUserRole("admin");
        when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()))
                .thenReturn(Optional.of(user));

        User savedAdminUser = userService.userLogin(user);
        System.out.println(savedAdminUser);
        // then - verify the output
        assertThat(savedAdminUser).isNotNull();
    }

    @Test
    public void givenExistingUsernameAndPassword_whenUserLogin_thenThrowsException(){
        // given - precondition or setup
        given(userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword()))
                .willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        Exception exception= assertThrows(InvalidLoginCredentialsException.class, () -> {
            userService.userLogin(user);
        });
        System.out.println(exception.getMessage());
        // then
        assertEquals("Login details are invalid. Please try with correct details.",exception.getMessage());
    }
}
