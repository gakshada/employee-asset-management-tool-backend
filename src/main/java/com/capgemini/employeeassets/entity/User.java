package com.capgemini.employeeassets.entity;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "userCredentials")
//@DiscriminatorColumn(name="userRole")
public class User {
    @Id
    @GeneratedValue
    private long userId;
    @Column(unique = true)
    @Email(message="Please enter your email as username", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    @NotNull(message="username is required")
    private String username;

    @Pattern(regexp= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",message="Password must be of minimum 8 and maximum 20 characters,at least one uppercase letter, one lowercase letter,one number and one special character ")
    private String password;
    private String userRole;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }



    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
