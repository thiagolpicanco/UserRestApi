package com.desafio.dto;

public class LoginDTO {

	private String email;

	private String password;

	public LoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
