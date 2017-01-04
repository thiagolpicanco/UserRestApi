package com.desafio.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dao.UserRepository;
import com.desafio.dto.JwtUserDTO;
import com.desafio.entity.Phone;
import com.desafio.entity.User;
import com.desafio.security.MD5Generator;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;

	/**
	 * Método responsavel por listar todos os Usuarios no Banco
	 * 
	 * @return usuarios persistidos no banco
	 */
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	/**
	 * Método responsavel por buscar usuario por email;
	 * 
	 * @param email
	 * @return Usuario encontado
	 */
	public User findByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * Método responsavel por buscar usuario por email;
	 * 
	 * @param email
	 * @return Usuario encontado
	 */
	public User findByID(final String id) {
		return userRepository.findById(id);
	}

	/**
	 * Método responsavel por verificar se usuario ja existe
	 * 
	 * @param email
	 * @return Boolean
	 */
	public Boolean userExists(final String email) {
		return findByEmail(email) != null;
	}

	/**
	 * Método Responsavel por cadastrar novo usuario
	 * 
	 * @param user
	 */
	public void saveUser(final User user) {
		final Date today = new Date();
		this.setPhoneUser(user);
		final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		user.setId(uuid);
		user.setPassword(MD5Generator.getMd5HashCode(user.getPassword()));
		user.setCreated(today);
		user.setModified(null);
		user.setLast_login(null);
		JwtUserDTO jwtUser = new JwtUserDTO(user.getId(), user.getEmail());
		String token = jwtService.getToken(jwtUser);
		user.setToken(token);
		userRepository.save(user);
	}

	/**
	 * Método responsável por atualizar os dados do usuario a cada Login
	 * 
	 * @param user
	 */
	public void updateLoginUser(final User user) {
		user.setLast_login(new Date());
		user.setModified(new Date());
		userRepository.save(user);
	}

	/**
	 * Método responsavel por vincular telefones a determinado usuario
	 * 
	 * @param user
	 * @param phone
	 */
	public void setPhoneUser(User user) {
		if (user.getPhones() != null && !user.getPhones().isEmpty()) {
			for (Phone tel : user.getPhones()) {
				tel.setUser(user);
			}
		}
	}

}
