package net.engineeringdigest.journelApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journelApp.entity.User;
import net.engineeringdigest.journelApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Ok";
	}
	
	@PostMapping("/create-user")
	public void createUser(@RequestBody User user) {
		userService.saveNewUser(user);
		
	}

}
