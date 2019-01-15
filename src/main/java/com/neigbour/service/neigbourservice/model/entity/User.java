package com.neigbour.service.neigbourservice.model.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Builder
@Getter
public class User {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name" , updatable = true, nullable = false, unique = true)
	private String name;
	
	@Column(name = "mail", updatable = true, nullable = false, unique = true)
	private String mail;
	
	@JsonIgnore
	@Column(name = "password", updatable = true, nullable = false)
	private String password;
	
	
	
	
	
}
