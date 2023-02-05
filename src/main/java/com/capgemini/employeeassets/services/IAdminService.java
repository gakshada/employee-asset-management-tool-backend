package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Admin;

import java.util.List;

public interface IAdminService {
    String addAdmin(Admin admin);
    List<Admin> getAllAdmin();
    Admin  getAdminById(long id);
    Admin updateAdminByName(long id,String name);
    Admin deleteAdminById(long id);
}
