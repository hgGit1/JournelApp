package net.engineeringdigest.journelApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journelApp.entity.User;
import net.engineeringdigest.journelApp.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		
		if(user != null) {
			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
			.username(user.getUserName())
			.password(user.getPassword())
			.roles(user.getRoles().toArray(new String[0]))
			.build();
			
			return userDetails;
		}
		// TODO Auto-generated method stub
		throw new UsernameNotFoundException("User not found for Username"+username);
	}
	

}
