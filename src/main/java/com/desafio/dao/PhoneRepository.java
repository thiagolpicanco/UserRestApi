package com.desafio.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.desafio.entity.Phone;

@RepositoryRestResource(collectionResourceRel = "Phone", path = "Phone")
public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long> {
	
	public Phone findUserByDddAndNumber(final Long ddd, final Long number);
	
}
