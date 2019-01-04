package com.neigbour.service.neigbourservice.util;

import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;

import java.util.Arrays;

public final class TestConstants {


    public static final Country CANADA = Country.builder()
            .nameFr("CanadaFr")
            .nameEn("CanadaEn")
            .cityList(Arrays.asList())
            .build();

    public static final Country FRANCE = Country.builder()
            .nameFr("FranceFr")
            .nameEn("FranceEn")
            .cityList(Arrays.asList())
            .build();
    public static final City MONTREAL = City.builder()
            .nameFr("MontrealFr")
            .nameEn("MontrealEn")
            .districtList(Arrays.asList())
            .country(CANADA)
            .build();
    public static final City TORONTO = City.builder()
            .nameFr("TorontoFr")
            .nameEn("TorontoEn")
            .districtList(Arrays.asList())
            .country(CANADA)
            .build();
    public static final District STHENRI = District.builder()
            .nameFr("STHenriFr")
            .nameEn("StHenriEn")
            .descriptionFr("DescriptionFr")
            .descriptionEn("DescriptionEn")
            .city(MONTREAL)
            .build();

    public static final PointOfInterest RITA = PointOfInterest
            .builder()
            .name("Rita")
            .address("5453 rue du cannard")
            .category(PointOfInterest.Category.RESTAURANT)
            .district(STHENRI)
            .build();

    // -------------- P O I --------------
    public static PointOfInterest restaurant1 = PointOfInterest.builder()
            .id(new Long(5465413))
            .name("restaurant1")
            .address("restaurant1")
            .category(PointOfInterest.Category.RESTAURANT)
            .build();
    public static PointOfInterest  restaurant2 = PointOfInterest.builder()
            .id(new Long(5465414))
            .name("restaurant2")
            .address("restaurant2")
            .category(PointOfInterest.Category.RESTAURANT)
            .build();
    public static PointOfInterest shop1 = PointOfInterest.builder()
            .id(new Long(5465415))
            .name("shop1")
            .address("shop1")
            .category(PointOfInterest.Category.SHOP)
            .build();

}
