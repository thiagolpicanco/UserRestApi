package com.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.dao.PhoneRepository;
import com.desafio.entity.Phone;
import com.desafio.entity.User;

@Component
public class PhoneService {

	/**
	 * CONTANTES
	 */
	final Integer DDD_DEFAULT_SIZE = 2;
	final Integer MAX_NUMBER_SIZE = 9;
	final Integer MIN_NUMBER_SIZE = 8;

	@Autowired
	private PhoneRepository phoneRepository;

	/**
	 * Método responsavel por verificar se telefones são validos
	 * 
	 * @param ddd
	 * @param Number
	 * @return Boolean
	 */
	public Boolean areThePhonesValids(final User user) {

		for (Phone telefone : user.getPhones()) {
			final Integer dddSize = telefone.getDdd().toString().length();
			final Integer numberSize = telefone.getNumber().toString().length();
			if (!dddSize.equals(DDD_DEFAULT_SIZE)) {
				return Boolean.FALSE;
			} else if (numberSize > MAX_NUMBER_SIZE || numberSize < MIN_NUMBER_SIZE) {
				return Boolean.FALSE;
			}
		}
		return true;
	}

	/**
	 * Método responsavel por retornar todos os telefones
	 * 
	 * @return Lista Telefones
	 */
	public List<Phone> findAll() {
		return (List<Phone>) phoneRepository.findAll();
	}

}
