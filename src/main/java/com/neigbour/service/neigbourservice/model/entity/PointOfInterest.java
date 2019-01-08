package com.neigbour.service.neigbourservice.model.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "point_of_interest")
public class PointOfInterest implements Serializable {

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

    @Column(name = "phone" , nullable = true, updatable = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;


    @Getter
    public enum Category{

        RESTAURANT("R","Restaurant", "Restaurant"),
        SHOP("S","Shop", "Commerce"),
        ASSOCIATION("ASS", "Association" , "Association"),
        ACTIVITY("AC", "Activité", "Activity");

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
                case "ASS":
                    return ASSOCIATION;
                case "AC":
                    return ACTIVITY;
                default:
                    throw new IllegalArgumentException("Unknown category " + categoryString);
            }

        }

    }


}
