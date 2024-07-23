package com.security.authentication_service.web;

import com.security.authentication_service.dto.RoleUserForm;
import com.security.authentication_service.entities.AppRole;
import com.security.authentication_service.entities.AppUser;
import com.security.authentication_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/addUser")
    public AppUser addNewUser(@RequestBody AppUser appUser){
        return accountService.addNewUser(appUser);
    }
    @PostMapping("/addRole")
    public AppRole addNewRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }
    @GetMapping("/allUsers")
    public List<AppUser> allUsers(){
        return accountService.listUser();
    }
    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }
    @DeleteMapping("/removeRoleToUser")
    public void removeRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.removeRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }
}
