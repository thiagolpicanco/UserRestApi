package com.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.desafio.dto.LoginDTO;
import com.desafio.entity.User;
import com.desafio.service.LoginService;
import com.desafio.service.PhoneService;
import com.desafio.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PhoneService phoneService;

	@Autowired
	private LoginService loginServiceService;

	@RequestMapping(value = "/users", produces = "application/JSON")
	public List<User> listUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

		if (userService.userExists(user.getEmail())) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		} else {
			if (phoneService.areThePhonesValids(user)) {
				userService.saveUser(user);
			}
		}

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody LoginDTO login, UriComponentsBuilder ucBuilder) {

		User user = loginServiceService.login(login);

		if (null != user) {

			return new ResponseEntity<User>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
