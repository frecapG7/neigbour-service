package com.neigbour.service.neigbourservice.model.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.neigbour.service.neigbourservice.model.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void should_insert_a_user(){
		
		User user = getTestUser();
		
		User result = userRepository.save(user);
		
		Assert.assertNotNull(result.getId());
		Assert.assertEquals(user.getName(), result.getName());
		Assert.assertEquals(user.getMail(), result.getMail());
		Assert.assertEquals(user.getPassword(), result.getPassword());
		
	}
	
	@Test
	public void should_find_a_user_by_userName(){
		User user = getTestUser();
		
		testEntityManager.persist(user);
		
		Assert.assertNotNull(userRepository.findUserByName(user.getName()));
	}
	
	@Test
	public void should_find_a_user_by_mail(){
		User user = getTestUser();
		
		testEntityManager.persist(user);
		Assert.assertNotNull(userRepository.findUserByMail(user.getMail()));
	}
	
	
	
	private User getTestUser(){
		return User
				.builder()
				.name("Florian")
				.mail("toto@gmail.com")
				.password("rkrkrk")
				.build();
	}
}
