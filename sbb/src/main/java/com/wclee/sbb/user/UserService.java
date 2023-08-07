package com.wclee.sbb.user;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wclee.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository; 
	private final PasswordEncoder passwordEncoder; 
	
	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser(); 
		user.setUsername(username);
		user.setEmail(email);
//		BCryptPasswordEncoder passowrdEncoder = new BCryptPasswordEncoder(); 
//		user.setPassword(passowrdEncoder.encode(password));
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user); 
		return user; 
		
	}
	
	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.userRepository.findByUsername(username); 
		if(siteUser.isPresent()) {
			return siteUser.get(); 
		} else {
			throw new DataNotFoundException("siteuser not found"); 
		}
	}
}
