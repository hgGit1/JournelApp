package net.engineeringdigest.journelApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.engineeringdigest.journelApp.entity.User;
import net.engineeringdigest.journelApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Disabled
	@ParameterizedTest
	@ArgumentsSource(UserArgumentsProvider.class)
	public void testSaveNewUser(User user) {
		assertTrue(userService.saveNewUser(user));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"Ram",
		"shyam",
		"vipul"
	})
	public void testFindByUserName(String name) {
		//assertEquals(7,5+3);
		assertNotNull(userRepository.findByUserName(name));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"1,2,3",
		"2,18,12",
		"3,3,9"
	})
	public void test(int a, int b, int expected) {
		assertEquals(expected, a+b);
	}

}
