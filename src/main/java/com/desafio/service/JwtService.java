package com.desafio.service;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.desafio.dto.JwtUserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${jwt.expire.hours}")
	private Long expireHours;

	@Value("${jwt.token.secret}")
	private String plainSecret;
	private String encodedSecret;

	@PostConstruct
	protected void init() {
		this.encodedSecret = generateEncodedSecret(this.plainSecret);
	}

	/**
	 * Método reponsavel por gerar o secredo criptografado
	 * 
	 * @param plainSecret
	 * @return
	 */
	protected String generateEncodedSecret(String plainSecret) {
		if (StringUtils.isEmpty(plainSecret)) {
			throw new IllegalArgumentException("Segredo não pode ser nulo");
		}
		return Base64.getEncoder().encodeToString(this.plainSecret.getBytes());
	}

	/**
	 * Método responsavel por obter a data de expiração do token
	 * 
	 * @return Data de expiração
	 */
	protected Date getExpirationTime() {
		Date now = new Date();
		Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
		return new Date(expireInMilis + now.getTime());
	}

	/**
	 * Método responsavel por obter o usuario do Token Informado
	 * 
	 * @param encodedSecret
	 * @param token
	 * @return Usuario
	 */
	protected JwtUserDTO getUser(String encodedSecret, String token) {
		Claims claims = Jwts.parser().setSigningKey(encodedSecret).parseClaimsJws(token).getBody();
		String id = claims.getSubject();
		String email = (String) claims.get("email");
		JwtUserDTO securityUser = new JwtUserDTO();
		securityUser.setId(id);
		securityUser.setEmail(email);
		return securityUser;
	}

	public JwtUserDTO getUser(String token) {
		return getUser(this.encodedSecret, token);
	}

	/**
	 * Método responsavel por gerar Toke n com base no id e email
	 * 
	 * @param encodedSecret
	 * @param jwtUser
	 * @return Token de acesso
	 */
	protected String getToken(String encodedSecret, JwtUserDTO jwtUser) {
		Date now = new Date();
		return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(jwtUser.getId())
				.claim("email", jwtUser.getEmail()).setIssuedAt(now).setExpiration(getExpirationTime())
				.signWith(SignatureAlgorithm.HS512, encodedSecret).compact();
	}

	public String getToken(JwtUserDTO jwtUser) {
		return getToken(this.encodedSecret, jwtUser);
	}
}
