package com.capgemini.employeeassets.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
//@PrimaryKeyJoinColumn(name = "userId")
//@DiscriminatorValue(value="employee")
public class Employee extends User{
//    @Id
//    private Long empId;
    @NotEmpty(message="Name cannot be Empty")
    @Size(min=2, message= "Name should be greater than 2 letters")
    private String empName;

    @NotEmpty(message="Address cannot be empty")
    private String empAddress;
    @NotEmpty(message="Contact cannot be empty")
    @Size(min=10,max=10, message= "Please enter valid contact number")
    private String empPhoneNumber;
    @NotEmpty(message="Designation cannot be empty")
    private String empDesignation;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpPhoneNumber() {
        return empPhoneNumber;
    }

    public void setEmpPhoneNumber(String empPhoneNumber) {
        this.empPhoneNumber = empPhoneNumber;
    }

    public String getEmpDesignation() {
        return empDesignation;
    }

    public void setEmpDesignation(String empDesignation) {
        this.empDesignation = empDesignation;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empName='" + empName + '\'' +
                ", empAddress='" + empAddress + '\'' +
                ", empPhoneNumber='" + empPhoneNumber + '\'' +
                ", empDesignation='" + empDesignation + '\'' +
                '}';
    }
}
