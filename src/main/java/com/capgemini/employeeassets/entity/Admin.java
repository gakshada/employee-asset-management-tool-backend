package com.capgemini.employeeassets.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
//@DiscriminatorValue(value="admin")
public class Admin extends User{
//    @Id
//    private long adminId;
    @NotEmpty(message="Name cannot be empty")
    @Size(min=2, message= "Name should be greater than 2 letters")
    private String adminName;
    @Column(unique=true)
    @NotEmpty(message="Contact cannot be empty")
    @Size(min=10,max=10, message= "Please enter valid contact number")
    private String adminContact;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminContact() {
        return adminContact;
    }

    public void setAdminContact(String adminContact) {
        this.adminContact = adminContact;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminName='" + adminName + '\'' +
                ", adminContact='" + adminContact + '\'' +
                '}';
    }
}
