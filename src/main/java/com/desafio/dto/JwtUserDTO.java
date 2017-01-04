package com.desafio.dto;

public class JwtUserDTO {

	private String id;
	private String email;

	public JwtUserDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public JwtUserDTO(String id, String email) {
		super();
		this.id = id;
		this.email = email;
	}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
}



}
