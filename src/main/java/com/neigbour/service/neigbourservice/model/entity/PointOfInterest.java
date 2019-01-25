package com.neigbour.service.neigbourservice.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;

@Builder
@Data
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="POI_SUBCATEGORY",
            joinColumns = @JoinColumn(name = "poi_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    )
    private List<SubCategory> subCategories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;


    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL, mappedBy = "pointOfInterest")
    private List<Item> items;
    
    //Define list of image of the point 
    @Lob
    @ElementCollection
    @CollectionTable(name = "image",joinColumns=@JoinColumn(name="OWNER_ID"))
    private List<byte[]> images;
    
    
}
