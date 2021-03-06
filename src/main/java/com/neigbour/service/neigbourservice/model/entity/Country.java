package com.neigbour.service.neigbourservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country implements Serializable {

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    @JsonIgnore
    private List<City> cityList;
}
