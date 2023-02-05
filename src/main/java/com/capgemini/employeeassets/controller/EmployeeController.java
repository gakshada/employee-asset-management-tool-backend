package com.capgemini.employeeassets.controller;


import com.capgemini.employeeassets.entity.Employee;
import com.capgemini.employeeassets.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @CrossOrigin
    @GetMapping("/employee-retrieve/{id}")
    public Employee retrieveEmployeeById(@PathVariable("id") long id)
    {
        return employeeService.getEmployeeById(id);
    }

    @CrossOrigin
    @PutMapping("/employee-update-address-and-phonenumber/{id}")
    public Employee updateEmplyeeAddressAndPnoneNumber(@PathVariable("id") long id , @RequestParam String address, @RequestParam String phoneNumber)
    {
        return employeeService.updateEmpAddressAndEmpPhoneNumber(id,address,phoneNumber);
    }

}
