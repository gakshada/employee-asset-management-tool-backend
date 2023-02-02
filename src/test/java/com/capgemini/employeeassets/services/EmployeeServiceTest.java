package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Admin;
import com.capgemini.employeeassets.entity.Employee;
import com.capgemini.employeeassets.exception.ExistingUsernameException;
import com.capgemini.employeeassets.exception.UserIdNotFoundException;
import com.capgemini.employeeassets.repository.EmployeeRepository;
import com.capgemini.employeeassets.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setup()
    {
        employee =new Employee();
        employee.setEmpName("Akshada");
        employee.setEmpAddress("Shirdi");
        employee.setEmpPhoneNumber("7689905645");
        employee.setEmpDesignation("Software Engineer");
        employee.setUserId(1L);
        employee.setUsername("akshada@gmail.com");
        employee.setPassword("Akshada@1234");
        employee.setUserRole("employee");
    }

    @DisplayName("JUnit test for addEmployee method")
    @Test
    public void addEmployee()
    {
        // given - precondition or setup
        given(userRepository.findByUsername(employee.getUsername()))
                .willReturn(Optional.empty());

        given(employeeRepository.save(employee)).willReturn(employee);

        // when -  action or the behaviour that we are going test
        String savedEmployee = employeeService.addEmployee(employee);

        System.out.println(savedEmployee);
        // then - verify the output
        assertThat(savedEmployee).isEqualTo("Employee added successfully");
    }

    @DisplayName("JUnit test for addEmployee method which throws exception")
    @Test
    public void givenExistingUsername_whenAddEmployee_thenThrowsException(){
        // given - precondition or setup
        given(userRepository.findByUsername(employee.getUsername()))
                .willReturn(Optional.of(employee));

        // when -  action or the behaviour that we are going test
       Exception exception= assertThrows(ExistingUsernameException.class, () -> {
            employeeService.addEmployee(employee);
        });

        // then
        assertEquals("Sorry Employee with this username already exists",exception.getMessage());
    }

    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void getEmployeeById(){
        // given
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // when
        Employee savedEmployee = employeeService.getEmployeeById(employee.getUserId());
        System.out.println(savedEmployee);
        // then
        assertThat(savedEmployee).isNotNull();

    }
    @DisplayName("JUnit test for getEmployeeById method which throws exception")
    @Test
    public void whenGetEmployeeById_thenThrowsException(){
        // given - precondition or setup
        given(employeeRepository.findById(employee.getUserId()))
                .willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        Exception exception = assertThrows(UserIdNotFoundException.class, () -> {
            employeeService.getEmployeeById(employee.getUserId());
        });

        // then
        assertEquals("Employee with this userId is not Found",exception.getMessage());
    }

    @DisplayName("JUnit test for getAllEmployee method")
    @Test
    public void getAllEmployee()
    {
        //given - precondition or setup
        Employee  employee1 =new Employee();
        employee1.setEmpName("Kalyani");
        employee1.setEmpAddress("Shirdi");
        employee1.setEmpPhoneNumber("7689306645");
        employee1.setEmpDesignation("Software Engineer");
        employee1.setUserId(2L);
        employee1.setUsername("kalyani@gmail.com");
        employee1.setPassword("Kalyani@1234");
        employee1.setUserRole("employee");

        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        // when -  action or the behaviour that we are going test
        List<Employee> employeeList = employeeService.getAllEmployee();
        System.out.println(employeeList);
        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for deleteEmployeeById method")
    @Test
    public void deleteEmployeeById(){
        // given
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // when
        Employee deletedEmployee = employeeService.deleteEmployeeById(employee.getUserId());
        System.out.println(deletedEmployee);
        // then
        assertThat(deletedEmployee).isNotNull();

    }

    @DisplayName("JUnit test for deleteEmployeeById method which throws exception")
    @Test
    public void whenDeleteEmployeeById_thenThrowsException(){
        // given
        given(employeeRepository.findById(1L)).willReturn(Optional.empty());

        // when
        Exception exception = assertThrows(UserIdNotFoundException.class, () -> {
            employeeService.deleteEmployeeById(employee.getUserId());
        });

        // then
        assertEquals("Employee with this userId is not Found",exception.getMessage());
    }
    //remaining tests- update

    @DisplayName("JUnit test for updateEmpName method")
    @Test
    public void updateEmpName(){
        String newName="kalyani";
        // given
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmpName(newName);
        // when
        Employee updatedEmployee = employeeService.updateEmpName(employee.getUserId(),newName);
        System.out.println(updatedEmployee);
        // then
        assertThat(updatedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for updateEmpName method which throws exception")
    @Test
    public void whenUpdateEmpName_thenThrowsException(){
        String newName="kalyani";
        // given
        given(employeeRepository.findById(1L)).willReturn(Optional.empty());

        // when
        Exception exception = assertThrows(UserIdNotFoundException.class, () -> {
            employeeService.updateEmpName(employee.getUserId(),newName);
        });

        // then
        assertEquals("Employee with this userId is not Found",exception.getMessage());
    }

    @DisplayName("JUnit test for updateEmpAddressAndEmpPhoneNumber method")
    @Test
    public void updateEmpAddressAndEmpPhoneNumber(){
        String newAddress="Gurgaon",newPhoneNumber="9897678990";
        // given
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmpAddress(newAddress);
        employee.setEmpPhoneNumber(newPhoneNumber);
        // when
        Employee updatedEmployee = employeeService.updateEmpAddressAndEmpPhoneNumber(employee.getUserId(),newAddress,newPhoneNumber);
        System.out.println(updatedEmployee);
        // then
        assertThat(updatedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for updateEmpAddressAndEmpPhoneNumber method which throws exception")
    @Test
    public void whenUpdateEmpAddressAndEmpPhoneNumber_thenThrowsException(){
        String newAddress="Gurgaon",newPhoneNumber="9897678990";
        // given
        given(employeeRepository.findById(1L)).willReturn(Optional.empty());

        // when
        Exception exception = assertThrows(UserIdNotFoundException.class, () -> {
            employeeService.updateEmpAddressAndEmpPhoneNumber(employee.getUserId(),newAddress,newPhoneNumber);
        });

        // then
        assertEquals("Employee with this userId is not Found",exception.getMessage());
    }
}
