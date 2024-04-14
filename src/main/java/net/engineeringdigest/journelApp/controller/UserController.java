package net.engineeringdigest.journelApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journelApp.entity.JournelEntry;
import net.engineeringdigest.journelApp.entity.User;
import net.engineeringdigest.journelApp.repository.UserRepository;
import net.engineeringdigest.journelApp.service.JournelEntryService;
import net.engineeringdigest.journelApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
//	@GetMapping 
//	public List<User> getAllUsers(){
//		return userService.getAll();
//	}
	
//	@PostMapping
//	public void createUser(@RequestBody User user) {
//		userService.saveEntry(user);
//	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User userInDb = userService.findByUserName(userName);
		if(userInDb != null) {
			userInDb.setUserName(user.getUserName());
			userInDb.setPassword(user.getPassword());
			userService.saveNewUser(userInDb);
		}
		return new ResponseEntity<>(userInDb, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<?> deleteUserById(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userRepository.deleteByUserName(authentication.getName());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
}
