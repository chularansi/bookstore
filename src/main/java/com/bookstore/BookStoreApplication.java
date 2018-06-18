package com.bookstore;

import com.bookstore.config.SecurityUtility;
import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Chularansi");
		user1.setLastName("Fernando");
		user1.setUsername("c");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("chularansifernando@yahoo.com");

		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");

		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user1, role1));

		userService.createUser(user1, userRoles);

		userRoles.clear();

		User user2 = new User();
		user2.setFirstName("Admin");
		user2.setLastName("Admin");
		user2.setUsername("admin");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("pwd"));
		user2.setEmail("admin@yahoo.com");

		Role role2 = new Role();
		role2.setRoleId(0);
		role2.setName("ROLE_ADMIN");

		userRoles.add(new UserRole(user2, role2));

		userService.createUser(user2, userRoles);
	}
}
