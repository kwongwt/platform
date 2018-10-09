package com.kwong.boot.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwong.boot.system.model.User;
import com.kwong.boot.system.repository.UserRepository;
import com.kwong.boot.system.service.UserService;

@Service
public class UserserviceImpl implements UserService{

	@Autowired  
    private UserRepository userRepository;
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);  
	}

}
