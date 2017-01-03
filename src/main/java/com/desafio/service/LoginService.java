package com.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dao.UserRepository;
import com.desafio.dto.LoginDTO;
import com.desafio.entity.User;
import com.desafio.security.MD5Generator;

@Component
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public User login(final LoginDTO loginDTO) {
		User user = userRepository.findByEmail((loginDTO.getEmail()));
		final String password = MD5Generator.getMd5HashCode(loginDTO.getPassword());

		if (null != user && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
