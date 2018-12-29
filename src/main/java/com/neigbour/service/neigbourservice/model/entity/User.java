package com.neigbour.service.neigbourservice.model.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
public class User {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "username" , updatable = true, nullable = false, unique = true)
	private String userName;
	
	@Column(name = "mail", updatable = true, nullable = false, unique = true)
	private String mail;
	
	@Column(name = "password", updatable = true, nullable = false)
	private String password;
	
	
	
	
	
}
