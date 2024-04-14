package net.engineeringdigest.journelApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journelApp.entity.JournelEntry;
import net.engineeringdigest.journelApp.entity.User;
import net.engineeringdigest.journelApp.repository.JournelEntryRepository;
import net.engineeringdigest.journelApp.repository.UserRepository;

@Component
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	public boolean saveNewUser(User myEntry) {
		
		try {
			myEntry.setPassword(passwordEncoder.encode(myEntry.getPassword()));
			myEntry.setRoles(Arrays.asList("USER"));
			userRepository.save(myEntry);
			return true;
		} catch (Exception e) {
			log.error("Throwing error in saving UserService");
			return false;
			// TODO: handle exception
		}
		
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public Optional<User> findById(ObjectId id) {
		return userRepository.findById(id);
		
	}
	
	public void deleteJournelById(ObjectId id) {
		userRepository.deleteById(id);
	}
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public void saveAdmin(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
		userRepository.save(user);
		
	}

}
