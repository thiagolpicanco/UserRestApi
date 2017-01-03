package com.desafio.dto;

public class MensagemErroDTO {

	

	private String mensagem;

	public MensagemErroDTO(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public MensagemErroDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
