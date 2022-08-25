package com.project.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private AuthUtils authUtils;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
		Map<String, Object> response = new HashMap<>();
		response.put("user", authUtils.generateJwtToken(user));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> register(@RequestBody User user){
		System.out.println(user.toString());
		return userService.register(user);
	}
}


//"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJydXBlc2hfcmF0aG9yZSIsImV4cCI6MTY2MTMwNDU0NywiaWF0IjoxNjYxMjY4NTQ3fQ.dieNDVmuoNtF8yQXQurFJEBYaHDKuT7oJ2wuN6-DU1c2pLSS7nMvWWqLsJm1L4l3y90Vr9tNHixNiF7iHHAQZA"