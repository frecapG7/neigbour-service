package com.neigbour.service.neigbourservice.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PointOfInterestCategory {

    RESTAURANT("R","Restaurant", "Restaurant"),
    SHOP("S","Shop", "Commerce"),
    ASSOCIATION("ASS", "Association" , "Association"),
    ACTIVITY("AC", "Activité", "Activity");

    private String id;
    private String nameFr;
    private String nameEn;


    PointOfInterestCategory(){}

    PointOfInterestCategory(String id, String nameEn, String nameFr){
        this.id = id;
        this.nameEn = nameEn;
        this.nameFr = nameFr;
    }

    public static final PointOfInterestCategory fromString(String categoryString) throws IllegalArgumentException{
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

    @Override
    public String toString() {
        return getId();
    }
}
