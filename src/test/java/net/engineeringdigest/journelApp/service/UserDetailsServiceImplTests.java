package net.engineeringdigest.journelApp.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import net.engineeringdigest.journelApp.entity.User;
import net.engineeringdigest.journelApp.repository.UserRepository;


public class UserDetailsServiceImplTests {
	
	@InjectMocks
	private UserDetailsServiceImpl userDetailsService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	@Disabled
	void loadUserByUsernameTest() {
		when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(new User.Builder().userName("Ram").password("isnfhdncjhd").roles(new ArrayList<>()).build());
		UserDetails user = userDetailsService.loadUserByUsername("Ram");
		Assertions.assertNotNull(user);
	}
	
	

}
