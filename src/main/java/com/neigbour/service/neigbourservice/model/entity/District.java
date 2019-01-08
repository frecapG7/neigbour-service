package com.neigbour.service.neigbourservice.model.entity;

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
public class District implements Serializable {

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

    @NonNull
    @Column(name = "descriptionFr", updatable = true, nullable = false)
    private String descriptionFr;

    @NonNull
    @Column(name = "descriptionEn", updatable = true, nullable = false)
    private String descriptionEn;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;





}
