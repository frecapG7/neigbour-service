package com.neigbour.service.neigbourservice.model.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Builder
@Entity
@Table(name = "point_of_interest")
public class PointOfInterest {





    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false , updatable = false, unique = true)
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @NonNull
    @Column(name = "address", nullable = false, updatable = true)
    private String address;

    @Column(name = "uri" , nullable = true, updatable = true)
    private String uri;

    @Enumerated(EnumType.STRING)
    private Category category;





    public enum Category{

        RESTAURANT("R","Restaurant", "Restaurant"),
        SHOP("S","Shop", "Commerce");

        private String id;
        private String nameFr;
        private String nameEn;

        private Category(String id, String nameEn, String nameFr){
            this.id = id;
            this.nameEn = nameEn;
            this.nameFr = nameFr;
        }

        public static final Category fromString(String categoryString) throws IllegalArgumentException{
            if(!(categoryString instanceof String)) throw new IllegalArgumentException("Only string are permitted");
            switch (categoryString){
                case "R":
                    return RESTAURANT;
                case "S":
                    return SHOP;
                default:
                    throw new IllegalArgumentException("Unknown category " + categoryString);
            }

        }

    }


}
