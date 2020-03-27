package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;


@Service
public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.example.demo.model.User user = userRepository.findById(username).orElse(null);
		if(user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());

//		if("manish".equals(username)) {
//			return new User("manish", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
//		}
//		else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
	}
	
//	public com.example.demo.model.User saveMyUser(com.example.demo.model.User user){
//		System.out.println("Inside saveMyUser Method");
//		com.example.demo.model.User newUser = new com.example.demo.model.User();
//		newUser.setUsername(user.getUsername());
//		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//		System.out.println(newUser.getUsername() + "-> " + newUser.getPassword());
//		return userRepository.save(newUser);
//	}
	
}
