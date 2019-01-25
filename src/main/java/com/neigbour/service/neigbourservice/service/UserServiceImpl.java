package com.neigbour.service.neigbourservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


import com.neigbour.service.neigbourservice.controller.exception.UserNotFoundException;
import com.neigbour.service.neigbourservice.model.entity.User;
import com.neigbour.service.neigbourservice.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void saveUserPicture(Long id, MultipartFile picture) {
		try {
			User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
			Byte[] bytes = new Byte[picture.getBytes().length];
			int i = 0;
			for (byte b : picture.getBytes()) {
				bytes[i++] = b;
			}

			user.setPicture(bytes);
			userRepository.save(user);

		} catch (IOException e) {
			log.error("Found an error while inserting new picture : {}", e);
		}

	}
}
