package com.project.backend.test;


import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.backend.User;
import com.project.backend.UserRepository;
import com.project.backend.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)

class UserServiceTest {

@Mock
private UserRepository userRepository;

@Autowired
private UserService userService;

@Test
void getAllUsers() {
	ArrayList<User> myList = new ArrayList<User>();
//	myList.add(new User(1,"Rupesh","Rathore","rupesh_rathore","rupesh@gmail.com","rupesh","8349719415"));

	Assertions.assertArrayEquals(myList.toArray(), userService.getAllUsers().toArray());
	}	
}