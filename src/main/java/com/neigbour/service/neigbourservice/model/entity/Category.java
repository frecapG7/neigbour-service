package com.neigbour.service.neigbourservice.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neigbour.service.neigbourservice.model.entity.District.DistrictBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "nameFr", updatable = true, nullable = false)
    private String nameFr;

    @Column(name = "nameEn", updatable = true, nullable = false)
    private String nameEn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @JsonIgnore
    List<SubCategory> subCategories;


}
