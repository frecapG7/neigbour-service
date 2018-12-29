package com.neigbour.service.neigbourservice.controller;

import java.util.Arrays;

import org.apache.commons.collections4.IteratorUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.neigbour.service.neigbourservice.model.entity.User;
import com.neigbour.service.neigbourservice.model.repository.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserRepository userRepositoryMock;
	
	User user1 = User.builder().userName("user1").mail("mail1@gmail.com").password("toto").build();
	User user2 = User.builder().userName("user2").mail("mail2@gmail.com").password("toto").build();

	
	@Before
	private void setUp(){
		Mockito.when(userRepositoryMock.findById(Mockito.anyLong())).thenReturn(user1);
		
	}
	
	@Test
	public void should_retrieve_list_of_users(){
		
	}

}
