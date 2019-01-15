package com.neigbour.service.neigbourservice.util;

import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterestCategory;

import java.util.Arrays;

public final class TestConstants {


    public static final Country CANADA = Country.builder()
            .id(new Long(1))
            .nameFr("CanadaFr")
            .nameEn("CanadaEn")
            .cityList(Arrays.asList())
            .build();

    public static final Country FRANCE = Country.builder()
            .id(new Long(2))
            .nameFr("FranceFr")
            .nameEn("FranceEn")
            .cityList(Arrays.asList())
            .build();
    public static final City MONTREAL = City.builder()
            .id(new Long(1))
            .nameFr("MontrealFr")
            .nameEn("MontrealEn")
            .districtList(Arrays.asList())
            .country(CANADA)
            .build();
    public static final City TORONTO = City.builder()
            .id(new Long(2))
            .nameFr("TorontoFr")
            .nameEn("TorontoEn")
            .districtList(Arrays.asList())
            .country(CANADA)
            .build();
    public static final District STHENRI = District.builder()
            .id(new Long(1))
            .nameFr("STHenriFr")
            .nameEn("StHenriEn")
            .descriptionFr("DescriptionFr")
            .descriptionEn("DescriptionEn")
            .city(MONTREAL)
            .build();
    public static final District VERDUN = District.builder()
            .id(new Long(2))
            .nameFr("VerdunFr")
            .nameEn("VerdunEn")
            .descriptionFr("DescriptionFr")
            .descriptionEn("DescriptionEn")
            .city(MONTREAL)
            .build();

    // -------------- P O I --------------
    public static final PointOfInterest RITA = PointOfInterest
            .builder()
            .name("Rita")
            .address("5453 rue du cannard")
            .phoneNumber("(438)989-1000")
            .category(PointOfInterestCategory.RESTAURANT)
            .district(STHENRI)
            .build();

    public static PointOfInterest PARISA = PointOfInterest.builder()
            .id(new Long(5465413))
            .name("PARISA")
            .address("PARISA")
            .phoneNumber("(514)451-2563")
            .category(PointOfInterestCategory.RESTAURANT)
            .district(STHENRI)
            .build();

    public static PointOfInterest AKA_FUJI = PointOfInterest.builder()
            .id(new Long(5465414))
            .name("AKA_FUJI")
            .address("AKA_FUJI")
            .phoneNumber("(514)524-5781")
            .category(PointOfInterestCategory.RESTAURANT)
            .district(STHENRI)
            .build();



}
