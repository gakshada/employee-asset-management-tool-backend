package com.capgemini.employeeassets.controller;

import com.capgemini.employeeassets.entity.Admin;
import com.capgemini.employeeassets.entity.Assets;
import com.capgemini.employeeassets.entity.Employee;
import com.capgemini.employeeassets.services.AdminService;
import com.capgemini.employeeassets.services.AssetService;
import com.capgemini.employeeassets.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AssetService assetService;

    //Admin services controlled by admin
    @PostMapping("/admin-save")
    public String saveAdmin(@RequestBody @Valid Admin admin)
    {
        return adminService.addAdmin(admin);
    }

    @CrossOrigin
    @GetMapping("/admin-retrieve")
    public List<Admin> retrieveAllAdmin()
    {
        return adminService.getAllAdmin();
    }

    // Employee services controlled by admin
    @CrossOrigin
    @PostMapping("/employee-save")
    public String saveEmployee(@RequestBody @Valid Employee emp)
    {
        return employeeService.addEmployee(emp);
    }

    @CrossOrigin
    @GetMapping("/employee-retrieve")
    public List<Employee> retrieveAllEmployees()
    {
        return employeeService.getAllEmployee();
    }

    @CrossOrigin
    @PutMapping("/employee-update-name/{id}")
    public Employee updateEmployeeName(@PathVariable("id") long id,@RequestParam String name)
    {
        return employeeService.updateEmpName(id,name);
    }

    @CrossOrigin
    @DeleteMapping("employee-delete/{id}")
    public String deleteEmpById(@PathVariable("id") long id)
    {

        assetService.deleteAssetByUserId(id);
        employeeService.deleteEmployeeById(id);
        return "Employee deleted Successfully";
    }

    //Asset services controlled by admin
    @CrossOrigin
    @PostMapping("/asset-save")
    public Assets saveAssets(@RequestBody @Valid Assets asset)
    {
        return assetService.addAssets(asset);
    }

    @CrossOrigin
    @GetMapping("asset-retrieve")
    public List<Assets> retrieveAllAssets()
    {
        return assetService.getAllAssets();
    }
    @CrossOrigin
    @GetMapping("asset-retrieve-itemnumber/{itemNum}")
    public Assets retrieveAssetsById(@PathVariable("itemNum") long itemNum)
    {
        return assetService.getAssetsById(itemNum);
    }
    @CrossOrigin
    @GetMapping("asset-retrieve/{id}")
    public List<Assets> retrieveAssetsByUserId(@PathVariable("id") long id)
    {
        return assetService.getAllAssetsByUserId(id);
    }

    @CrossOrigin
    @PutMapping("asset-update-status/{serialNumber}")
    public Assets updateAssetsStatus(@PathVariable("serialNumber") long serialNumber, @RequestParam String status)
    {
        return assetService.updateAssetStatus(serialNumber,status);
    }

    @DeleteMapping("asset-delete/{userId}")
    public String deleteAssetDetailsByUserId(@PathVariable("userId") long userId)
    {
        return assetService.deleteAssetByUserId(userId);
    }
}
