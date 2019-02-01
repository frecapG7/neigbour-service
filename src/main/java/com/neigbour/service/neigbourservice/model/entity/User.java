package com.neigbour.service.neigbourservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
	
	@JsonIgnore
	@Lob
	@Column(name = "picture", updatable = true, nullable = true)
	private byte[] picture;
	
	
	
	
	
}
