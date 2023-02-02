package com.capgemini.employeeassets.services;

import com.capgemini.employeeassets.entity.Admin;

public interface IAdminService {
    String addAdmin(Admin admin);
    Admin  getAdminById(long id);
    Admin updateAdminByName(long id,String name);
    Admin deleteAdminById(long id);
}
