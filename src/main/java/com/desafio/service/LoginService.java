package com.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dao.UserRepository;
import com.desafio.dto.LoginDTO;
import com.desafio.entity.User;

@Component
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public User login(final LoginDTO loginDTO) {
		User user = userRepository.findByEmail((loginDTO.getEmail()));
		if (null != user) {
			return user;
		} else if (user.getPassword().equals(loginDTO.getPassword())) {
			return null;
		}
		return null;
	}

}
