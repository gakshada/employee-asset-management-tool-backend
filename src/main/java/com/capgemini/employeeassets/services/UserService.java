package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.User;
import com.capgemini.employeeassets.exception.InvalidLoginCredentialsException;
import com.capgemini.employeeassets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

//    public User addUser(User user)
//    {
//        return userRepository.save(user);
//    }
    @Override
    public User userLogin(User user)
    {
        Optional<User> opt=userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
       if(opt.isPresent())
       {
           User existingUser = opt.get();
           if(existingUser.getUserRole().equals("admin"))
           {
               System.out.println("Admin Login Successfully!!");
           }
           else{
               System.out.println("Employee Login Successfully!!");
           }
           return existingUser;
       }
       else
            throw new InvalidLoginCredentialsException("Login details are invalid. Please try with correct details.");
    }

}
