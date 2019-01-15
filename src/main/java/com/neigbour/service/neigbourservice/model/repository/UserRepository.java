package com.neigbour.service.neigbourservice.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neigbour.service.neigbourservice.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findUserByName(String userName);
	
	public User findUserByMail(String mail);

}
