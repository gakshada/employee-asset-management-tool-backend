package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Admin;
import com.capgemini.employeeassets.entity.Employee;
import com.capgemini.employeeassets.exception.ExistingUsernameException;
import com.capgemini.employeeassets.exception.UserIdNotFoundException;
import com.capgemini.employeeassets.repository.EmployeeRepository;
import com.capgemini.employeeassets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public String addEmployee(Employee emp)
    {
        if(!userRepository.findByUsername(emp.getUsername()).isPresent()) {
            employeeRepository.save(emp);
            return "Employee added successfully";
        }
        else{
            throw new ExistingUsernameException( "Sorry Employee with this username already exists");
        }
    }
    @Override
    public Employee getEmployeeById(long id)
    {
        if(employeeRepository.findById(id).isPresent())
            return employeeRepository.findById(id).get();
        else
            throw new UserIdNotFoundException("Employee with this userId is not Found");
    }
    @Override
    public List<Employee> getAllEmployee()
    {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmpAddressAndEmpPhoneNumber(long id,String address,String phoneNumber)
    {
        Optional<Employee> opt = employeeRepository.findById(id);
        if(opt.isPresent())
        {
            Employee existingEmployee=opt.get();
            existingEmployee.setEmpAddress(address);
            existingEmployee.setEmpPhoneNumber(phoneNumber);
            return employeeRepository.save(existingEmployee);
        }
        else
            throw new UserIdNotFoundException("Employee with this userId is not Found");
    }
    @Override
    public Employee updateEmpName(long id,String name)
    {
        Optional<Employee> opt = employeeRepository.findById(id);
        if(opt.isPresent())
        {
            Employee existingEmployee=opt.get();
            existingEmployee.setEmpName(name);
            return employeeRepository.save(existingEmployee);
        }
       else
            throw new UserIdNotFoundException("Employee with this userId is not Found");
    }
    @Override
    public Employee deleteEmployeeById(long id)
    {
        Optional<Employee> opt = employeeRepository.findById(id);
        if(opt.isPresent())
        {
            Employee existingEmployee=opt.get();
            employeeRepository.delete(existingEmployee);
            return existingEmployee;
        }
       else
           throw new UserIdNotFoundException("Employee with this userId is not Found");
    }
}
