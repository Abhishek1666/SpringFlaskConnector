package com.diseasepredictor.detection.implementation;

import org.springframework.stereotype.Service;

import com.diseasepredictor.detection.model.User;
import com.diseasepredictor.detection.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User registerUser(User user) {
		return userRepository.save(user);
	}
}
