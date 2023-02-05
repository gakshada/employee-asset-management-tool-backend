package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Admin;
import com.capgemini.employeeassets.exception.ExistingUsernameException;
import com.capgemini.employeeassets.exception.UserIdNotFoundException;
import com.capgemini.employeeassets.repository.AdminRepository;
import com.capgemini.employeeassets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService{
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public String addAdmin(Admin admin)
    {
        if(!userRepository.findByUsername(admin.getUsername()).isPresent())
        {
            adminRepository.save(admin);
            return "Admin added Successfully";
        }
        else
        throw new ExistingUsernameException( "Username exist already") ;

    }
    @Override
    public List<Admin> getAllAdmin()
    {
        return adminRepository.findAll();
    }
    @Override
    public Admin getAdminById(long id)
    {
        if(adminRepository.findById(id).isPresent())
            return adminRepository.findById(id).get();
        else
            throw new UserIdNotFoundException("Admin with this userId is not Found");
    }
    @Override
    public Admin updateAdminByName(long id,String name)
    {
       Optional<Admin> opt = adminRepository.findById(id);
        if(opt.isPresent())
        {
            Admin existingAdmin=opt.get();
            existingAdmin.setAdminName(name);
            return adminRepository.save(existingAdmin);
        }
        throw new UserIdNotFoundException("Admin with this userId is not Found");
    }
    @Override
    public Admin deleteAdminById(long id)
    {
        Optional<Admin> opt = adminRepository.findById(id);
        if(opt.isPresent())
        {
            Admin existingAdmin=opt.get();
            adminRepository.delete(existingAdmin);
            return existingAdmin;
        }
       throw new UserIdNotFoundException("Admin with this userId is not Found");
    }
}
