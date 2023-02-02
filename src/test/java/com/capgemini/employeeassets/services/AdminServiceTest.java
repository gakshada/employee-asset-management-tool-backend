package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Admin;
import com.capgemini.employeeassets.entity.Employee;
import com.capgemini.employeeassets.exception.ExistingUsernameException;
import com.capgemini.employeeassets.exception.UserIdNotFoundException;
import com.capgemini.employeeassets.repository.AdminRepository;
import com.capgemini.employeeassets.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminService adminService;

    private Admin admin;
    @BeforeEach
    void setup()
    {
        admin=new Admin();
        admin.setAdminName("Akshada");
        admin.setAdminContact("7890564534");
        admin.setUserId(1L);
        admin.setUsername("akshada@gmail.com");
        admin.setPassword("Akshada@1234");
        admin.setUserRole("admin");
    }

    @DisplayName("JUnit test for addAdmin method")
    @Test
    public void addAdmin()
    {
        // given - precondition or setup
        given(userRepository.findByUsername(admin.getUsername()))
                .willReturn(Optional.empty());

        given(adminRepository.save(admin)).willReturn(admin);

        // when -  action or the behaviour that we are going test
        String savedAdmin = adminService.addAdmin(admin);

        System.out.println(savedAdmin);
        // then - verify the output
        assertThat(savedAdmin).isEqualTo("Admin added Successfully");
    }

    @DisplayName("JUnit test for addAdmin method which throws exception")
    @Test
    public void givenExistingUsername_whenAddAdmin_thenThrowsException() {
        // given - precondition or setup
        given(userRepository.findByUsername(admin.getUsername()))
                .willReturn(Optional.of(admin));

        // when -  action or the behaviour that we are going test
        Exception exception = assertThrows(ExistingUsernameException.class, () -> {
            adminService.addAdmin(admin);
        });
        // then
        assertEquals("Username exist already", exception.getMessage());
    }

    @DisplayName("JUnit test for getAdminById method")
    @Test
    public void getAdminById(){
        // given
        given(adminRepository.findById(1L)).willReturn(Optional.of(admin));

        // when
        Admin savedAdmin = adminService.getAdminById(admin.getUserId());
        System.out.println(savedAdmin);
        // then
        assertThat(savedAdmin).isNotNull();

    }
    @DisplayName("JUnit test for getAdminById method which throws exception")
    @Test
    public void whenGetAdminById_thenThrowsException(){
        // given - precondition or setup
        given(adminRepository.findById(admin.getUserId()))
                .willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        Exception exception = assertThrows(UserIdNotFoundException.class, () -> {
            adminService.getAdminById(admin.getUserId());
        });

        // then
        assertEquals("Admin with this userId is not Found",exception.getMessage());
    }

    @DisplayName("JUnit test for deleteAdminById method")
    @Test
    public void deleteAdminById(){
        // given
        given(adminRepository.findById(1L)).willReturn(Optional.of(admin));

        // when
        Admin deletedAdmin = adminService.deleteAdminById(admin.getUserId());
        System.out.println(deletedAdmin);
        // then
        assertThat(deletedAdmin).isNotNull();

    }

    @DisplayName("JUnit test for deleteAdminById method which throws exception")
    @Test
    public void whenDeleteAdminById_thenThrowsException(){
        // given
        given(adminRepository.findById(1L)).willReturn(Optional.empty());

        // when
        Exception exception = assertThrows(UserIdNotFoundException.class, () -> {
            adminService.deleteAdminById(admin.getUserId());
        });

        // then
        assertEquals("Admin with this userId is not Found",exception.getMessage());
    }

    @DisplayName("JUnit test for updateAdminByName method")
    @Test
    public void updateAdminByName(){
        String newName="kalyani";
        // given
        given(adminRepository.findById(1L)).willReturn(Optional.of(admin));
        given(adminRepository.save(admin)).willReturn(admin);
        admin.setAdminName(newName);
        // when
        Admin updatedAdmin = adminService.updateAdminByName(admin.getUserId(),newName);
        System.out.println(updatedAdmin);
        // then
        assertThat(updatedAdmin).isNotNull();
    }

    @DisplayName("JUnit test for updateAdminByName method which throws exception")
    @Test
    public void whenUpdateAdminByName_thenThrowsException(){
        String newName="kalyani";
        // given
        given(adminRepository.findById(1L)).willReturn(Optional.empty());

        // when
        Exception exception = assertThrows(UserIdNotFoundException.class, () -> {
            adminService.updateAdminByName(admin.getUserId(),newName);
        });

        // then
        assertEquals("Admin with this userId is not Found",exception.getMessage());
    }
}
