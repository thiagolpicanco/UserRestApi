package com.desafio.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.desafio.dto.JwtUserDTO;
import com.desafio.dto.LoginDTO;
import com.desafio.dto.MensagemErroDTO;
import com.desafio.entity.User;
import com.desafio.security.MD5Generator;
import com.desafio.service.JwtService;
import com.desafio.service.PhoneService;
import com.desafio.service.UserService;
import com.desafio.util.DateUtil;

@RestController
public class UserController {

	final String INVALID_LOGIN = "Usuário e/ou senha inválidos";
	final String INVALID_ID = "ID de Usuário inválido ou não encontrado";
	final String NOT_AUTH = "Não autorizado";
	final String INVALID_PHONE = "Lista de Telefones possui algum Telefone inválido";
	final String USER_EXISTS = "E-mail já existente";
	final String INVALID_SESSION = "Sessão inválida";

	@Value("${jwt.auth.header}")
	String authHeader;

	@Autowired
	private UserService userService;

	@Autowired
	private PhoneService phoneService;

	@Autowired
	private JwtService jwtService;

	@RequestMapping(value = "publico/users", produces = "application/JSON")
	public List<User> listUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/usuario/{id}", produces = "application/JSON", method = RequestMethod.GET)
	public ResponseEntity<?> getUserProfile(@PathVariable("id") String id, HttpServletRequest request) {
		final String authHeaderVal = request.getHeader(authHeader);

		try {
			JwtUserDTO jwtUser = jwtService.getUser(authHeaderVal);
			if (null != jwtUser && !jwtUser.getId().equals(id)) {
				MensagemErroDTO msg = new MensagemErroDTO(NOT_AUTH);
				return new ResponseEntity<MensagemErroDTO>(msg, HttpStatus.UNAUTHORIZED);
			} else {
				User user = userService.findByID(id);
				final Date loginTime30m = DateUtil.getDateAfter30Min(user.getLast_login());
				if (loginTime30m.before(new Date())) {
					MensagemErroDTO msg = new MensagemErroDTO(INVALID_SESSION);
					return new ResponseEntity<MensagemErroDTO>(msg, HttpStatus.UNAUTHORIZED);
				} else {
					return new ResponseEntity<User>(user, HttpStatus.OK);
				}

			}

		} catch (Exception e) {
			MensagemErroDTO msg = new MensagemErroDTO(NOT_AUTH);
			return new ResponseEntity<MensagemErroDTO>(msg, HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {

		if (userService.userExists(user.getEmail())) {
			MensagemErroDTO msg = new MensagemErroDTO(USER_EXISTS);
			return new ResponseEntity<MensagemErroDTO>(msg, HttpStatus.CONFLICT);
		} else {
			if (phoneService.areThePhonesValids(user)) {
				userService.saveUser(user);
			} else {
				MensagemErroDTO msg = new MensagemErroDTO(INVALID_PHONE);
				return new ResponseEntity<MensagemErroDTO>(msg, HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody LoginDTO login, UriComponentsBuilder ucBuilder) {
		final User user = userService.findByEmail((login.getEmail()));
		final String password = MD5Generator.getMd5HashCode(login.getPassword());
		if (null != user && user.getPassword().equals(password)) {
			userService.updateLoginUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else if (null != user && !user.getPassword().equals(password)) {
			MensagemErroDTO msg = new MensagemErroDTO(INVALID_LOGIN);
			return new ResponseEntity<MensagemErroDTO>(msg, HttpStatus.UNAUTHORIZED);
		} else {
			MensagemErroDTO msg = new MensagemErroDTO(INVALID_LOGIN);
			return new ResponseEntity<MensagemErroDTO>(msg, HttpStatus.NOT_FOUND);
		}

	}

}
