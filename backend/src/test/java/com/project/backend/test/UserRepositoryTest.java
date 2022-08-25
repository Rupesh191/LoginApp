package com.project.backend.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.backend.User;
import com.project.backend.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired private UserRepository repository;
	
	@BeforeEach
	public void setUp() {
		repository.deleteAll();
		repository.resetIdValue();
	}
	
	@Test
	public void register() {
		User user = new User();
		user.setEmail("rupeshrathore@gmail.com");
		user.setFirstName("Rupesh");
		user.setLastName("Rathore");
		user.setUsername("rupesh25");
		user.setPassword("rupesh");
		user.setNumber("8349719415");
		User savedUser = repository.save(user);
		assertThat(savedUser.getId()).isEqualTo(1);

	    assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void login() {
		User user = new User();
		user.setUsername("rupesh25");
		user.setPassword("rupesh");
		repository.save(user);
		User fetchedUser = repository.findByUsernameAndPassword("rupesh25", "rupesh");
		assertEquals("rupesh25", fetchedUser.getUsername());
	}
}
