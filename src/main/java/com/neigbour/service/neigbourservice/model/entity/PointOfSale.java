package com.neigbour.service.neigbourservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.NonNull;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@SuperBuilder
public abstract class PointOfSale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", updatable=false, nullable=false)
	protected Long id;
	
	@NonNull
	@Column(name="name", nullable=false)
	public String name;
	
	@NonNull
	@Column(name="description", nullable=false)
	public String description;
	
	@NonNull
	@Column(name="address")
	public String address;
	
}
