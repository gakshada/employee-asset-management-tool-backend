package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Admin;
import com.capgemini.employeeassets.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    String addEmployee(Employee emp);
    List<Employee> getAllEmployee();
    Employee getEmployeeById(long id);

    Employee updateEmpAddressAndEmpPhoneNumber(long id,String address,String phoneNumber);
    Employee updateEmpName(long id ,String name);
    Employee deleteEmployeeById(long id);

}
