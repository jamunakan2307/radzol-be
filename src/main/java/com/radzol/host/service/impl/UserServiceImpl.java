/**
 * 
 */
package com.radzol.host.service.impl;

import org.springframework.stereotype.Service;

import com.radzol.host.model.User;
import com.radzol.host.repository.UserRepository;
import com.radzol.host.service.UserService;

/**
 * @author pradeepan
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
