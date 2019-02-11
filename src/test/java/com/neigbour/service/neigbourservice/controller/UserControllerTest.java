package com.neigbour.service.neigbourservice.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neigbour.service.neigbourservice.controller.exception.UserNotFoundException;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.apache.commons.collections4.IteratorUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.neigbour.service.neigbourservice.model.entity.User;
import com.neigbour.service.neigbourservice.model.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
@ComponentScan(basePackages = "com.neigbour.service.neigbourservice.controller.assembler")
public class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserRepository userRepositoryMock;
	


	ObjectMapper objectMapper;

	@Before
	public void setUp(){
		objectMapper = new ObjectMapper();
	}

	@Test
	public void should_find_a_user() throws Exception{
		Mockito.when(userRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(TestConstants.USER_1));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/users/{id}", new Long(45468546))
				.accept(MediaType.APPLICATION_JSON);


		mockMvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


	@Test
	public void should_thrown_a_user_not_found_exception_when_getting_user_id() throws Exception{
		Mockito.when(userRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/users/{id}/picture", new Long(45468546))
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}


	@Test
	public void should_create_a_user()throws Exception{
		Mockito.when(userRepositoryMock.save(Mockito.any(User.class))).thenReturn(TestConstants.USER_1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/neigbour/api/users")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(TestConstants.USER_1))
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void should_return_a_picture() throws Exception{
		User expected = TestConstants.USER_1;
		File picture = new File(getClass().getClassLoader().getResource("Koala.jpg").getFile());
		expected.setPicture(Files.readAllBytes(picture.toPath()));
		Mockito.when(userRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/users/{id}/picture", new Long(45468546))
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void should_thrown_a_user_not_found_exception_when_getting_picture() throws Exception{
		Mockito.when(userRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/users/{id}/picture", new Long(45468546))
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void should_update_a_user_picture() throws Exception{
		Mockito.when(userRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(TestConstants.USER_1));

		Mockito.when(userRepositoryMock.save(Mockito.any(User.class))).thenReturn(TestConstants.USER_1);
		MockMultipartFile picture = new MockMultipartFile("pictureFile", getClass().getClassLoader().getResource("Koala.jpg").getFile().getBytes());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/neigbour/api/users/{id}/picture", new Long(53135))
				.file(picture)
				.contentType(MediaType.IMAGE_JPEG);

		mockMvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}







}
