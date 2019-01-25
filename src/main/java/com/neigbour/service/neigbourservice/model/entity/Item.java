package com.neigbour.service.neigbourservice.model.entity;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "item")
@Data
public class Item {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "nameFr", nullable = false, updatable = true)
	private String nameFr;
	
	@Column(name = "nameEn", nullable = false, updatable = true)
	private String nameEn;
	
	@Column(name = "description", nullable = true, updatable = true)
	private String description;
	
	@Column(name = "price", nullable = false, updatable = true)
	private BigDecimal price;
	
	@JsonIgnore
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "poi_id", referencedColumnName = "id")
	private PointOfInterest pointOfInterest;

	
}
