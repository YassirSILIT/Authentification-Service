package com.security.authentication_service.security;

import com.security.authentication_service.entities.AppUser;
import com.security.authentication_service.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
    public class SecurityConfig{

    private AccountService accountService;

    public SecurityConfig(AccountService accountService) {
        this.accountService = accountService;
    }

    /*protected void configure(AuthenticationManagerBuilder auth)throws Exception{
            auth.userDetailsService(new UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                    AppUser appUser = accountService.loadUserByUsername(username);
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    appUser.getAppRoles().forEach(r->{
                        authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
                    });
                    return new User(appUser.getUsername(),appUser.getPassword(),authorities);
                }
            });
            }*/

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
            return httpSecurity
                    .formLogin(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults())
                    .authorizeHttpRequests(ar->ar.requestMatchers("/auth/**").permitAll())
                    .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                    .build();
        }


    }
