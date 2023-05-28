package com.lax.security;

import com.lax.security.Repository.UserRepository;
import com.lax.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootSecurityApplication implements CommandLineRunner {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUserName("brad");
		user.setPassword(this.bCryptPasswordEncoder.encode("pet"));
		user.setEmail("pet@gmail.com");
		user.setRole("ROLE_NORMAL");
		userRepository.save(user);

		User user1 = new User();
		user1.setUserName("john");
		user1.setPassword(this.bCryptPasswordEncoder.encode("cena"));
		user1.setEmail("john@gmail.com");
		user1.setRole("ROLE_ADMIN");
		userRepository.save(user1);

	}
}
