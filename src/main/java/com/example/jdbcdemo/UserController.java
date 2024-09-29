package com.example.jdbcdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/users")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping("init")
	public ResponseEntity<String> initUser() {
		repository.createTable();
		return ResponseEntity.ok("Table Created Successfully");
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		 return  ResponseEntity.ok(repository.getAllUsers()) ;
	}
	
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User user) {
		repository.addUser(user);
		return ResponseEntity.ok("User Added Successfully");
	}
	
	@PutMapping
	public ResponseEntity<String>  updateUser(@RequestParam("id") Integer id, 
										@RequestParam("email") String email) {
		repository.updateUserEmail(id,email);
		return ResponseEntity.ok("User updated Successfully");
	}
	
	@DeleteMapping
	public ResponseEntity<String>  deleteUser(@RequestParam("id") Integer id) {
		repository.deleteUserById(id); 
		return ResponseEntity.ok("User deleted Successfully");
	}

	
}
