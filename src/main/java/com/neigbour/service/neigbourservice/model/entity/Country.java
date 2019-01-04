package com.neigbour.service.neigbourservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "nameFr", updatable = true, nullable = false)
    private String nameFr;

    @NonNull
    @Column(name = "nameEn", updatable = true, nullable = false)
    private String nameEn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<City> cityList;
}