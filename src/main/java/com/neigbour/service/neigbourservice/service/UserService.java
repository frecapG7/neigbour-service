package com.neigbour.service.neigbourservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {

	
	public void saveUserPicture(Long id, MultipartFile picture);
}
