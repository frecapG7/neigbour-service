package com.neigbour.service.neigbourservice.configuration;


import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterestCategory;
import com.neigbour.service.neigbourservice.model.repository.CityRepository;
import com.neigbour.service.neigbourservice.model.repository.CountryRepository;
import com.neigbour.service.neigbourservice.model.repository.DistrictRepository;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(CountryRepository countryRepository,
                                   CityRepository cityRepository,
                                   DistrictRepository districtRepository,
                                   PointOfInterestRepository pointOfInterestRepository){
        return args -> {
            // ------------------- COUNTRY -------------------------
            log.info("Preloading countries into database");
            Country canada = countryRepository.save(Country.builder()
                    .nameFr("Canada")
                    .nameEn("Canada")
                    .build());
            Country france = countryRepository.save(Country.builder()
                    .nameFr("France")
                    .nameEn("France")
                    .build());

            // ------------------- CITIES -------------------------
            log.info("Preloading cities into database");
            City montreal = cityRepository.save(City.builder()
                    .nameFr("Montréal")
                    .nameEn("Montreal")
                    .country(canada)
                    .build());
            City toronto = cityRepository.save(City.builder()
                    .nameFr("Toronto")
                    .nameEn("Toronto")
                    .country(canada)
                    .build());


            // ------------------- district -------------------------
            log.info("Preloading districts into database");
            District verdun = districtRepository.save(District.builder()
                    .nameFr("Verdun")
                    .nameEn("Verdun")
                    .descriptionFr("ewgwergerg")
                    .descriptionEn("dqwefwerfgwer")
                    .city(montreal)
                    .build());

            // ------------------- POI - RESTAURANT -------------------------
            log.info("Preloading districts into database");
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Chez Boss & Fils")
                    .address("3610 Rue Wellington, Verdun")
                    .uri("chezbossetfils.ca")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)508-3457")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Restaurant Wellington")
                    .address("3629 Rue Wellington, Verdun")
                    .uri("restaurantwellington.com")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)419-1646")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Les Street Monkeys")
                    .address("3625 Rue Wellington, Verdun")
                    .uri("streetmonkeys.ca")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)768-1818")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Les Street Monkeys")
                    .address("3625 Rue Wellington, Verdun")
                    .uri("streetmonkeys.ca")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)768-1818")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Rita")
                    .address("3681 Rue Wellington, Verdun")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)768-1818")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Le grill J''m la Frite")
                    .address("350 Rue Strathmore, Verdun")
                    .uri("jmlafrite.com")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)769-3132")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Poutine LaFleur")
                    .address("3665 Rue Wellington, Verdun")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)658-9071")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Parisa")
                    .address("4123 Rue de Verdun, Verdun")
                    .uri("restaurantparisa.ca")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)768-7777")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Pigor")
                    .address("3780 Rue Wellington, Verdun")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)907-0816")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("La Queue de Poisson")
                    .address("3779 Rue Wellington, Verdun")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)507-8245")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Delhi Express")
                    .address("3876 Rue Wellington, Verdun")
                    .uri("nouveau-delhi.com")
                    .category(PointOfInterestCategory.RESTAURANT)
                    .phoneNumber("(514)767-6964")
                    .district(verdun)
                    .build());





        };
    }
}
