package com.security.authentication_service;

import com.security.authentication_service.entities.AppRole;
import com.security.authentication_service.entities.AppUser;
import com.security.authentication_service.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}
	//@Bean
	CommandLineRunner commandLineRunner(AccountService accountService){
		return args -> {
			accountService.addNewRole(new AppRole(null,"USER"));
			accountService.addNewRole(new AppRole(null,"ADMIN"));
			accountService.addNewRole(new AppRole(null,"MANAGER"));

			accountService.addNewUser(new AppUser(null,"user1","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"admin","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"user2","1234",new ArrayList<>()));

			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("user1","ADMIN");
			accountService.addRoleToUser("admin","ADMIN");
			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("user2","ADMIN");
			accountService.addRoleToUser("user2","MANAGER");
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
