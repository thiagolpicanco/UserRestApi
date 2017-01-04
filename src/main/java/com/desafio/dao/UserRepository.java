package com.desafio.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.desafio.entity.User;

@RepositoryRestResource(collectionResourceRel = "User", path = "User")
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);
	
	public User findById(String id);
	
}
