package com.security.authentication_service.service;

import com.security.authentication_service.entities.AppRole;
import com.security.authentication_service.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {

    private AccountService accountService;

    public UserDetailServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if (appUser != null) throw new UsernameNotFoundException("User not found");
        return User.
                withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getAppRoles().stream().map(AppRole:: getRoleName).toArray(String[]::new))
                .build();
    }
}
