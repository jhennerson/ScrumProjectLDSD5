package com.ifsp.scrumProjectLDSD5.JPA;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifsp.scrumProjectLDSD5.entity.User;
import com.ifsp.scrumProjectLDSD5.repository.UserRepository;

@Component
public class UserJPA {

	
	@Autowired
	private UserRepository userRepository;
	
	
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}
}
