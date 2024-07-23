package com.security.authentication_service.service;

import com.security.authentication_service.entities.AppRole;
import com.security.authentication_service.entities.AppUser;

import java.util.List;

public interface AccountService{
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    void removeRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUser();

}
