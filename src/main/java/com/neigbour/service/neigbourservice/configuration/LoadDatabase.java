package com.neigbour.service.neigbourservice.configuration;


import com.neigbour.service.neigbourservice.model.entity.Category;
import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterestCategory;
import com.neigbour.service.neigbourservice.model.entity.SubCategory;
import com.neigbour.service.neigbourservice.model.repository.CategoryRepository;
import com.neigbour.service.neigbourservice.model.repository.CityRepository;
import com.neigbour.service.neigbourservice.model.repository.CountryRepository;
import com.neigbour.service.neigbourservice.model.repository.DistrictRepository;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import com.neigbour.service.neigbourservice.model.repository.SubCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@Slf4j
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(CountryRepository countryRepository,
                                   CityRepository cityRepository,
                                   DistrictRepository districtRepository,
                                   CategoryRepository categoryRepository,
                                   SubCategoryRepository subCategoryRepository,
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
            // ------------------- DISTRICTS -------------------------
            log.info("Preloading districts into database");
            District verdun = districtRepository.save(District.builder()
                    .nameFr("Verdun")
                    .nameEn("Verdun")
                    .descriptionFr("ewgwergerg")
                    .descriptionEn("dqwefwerfgwer")
                    .city(montreal)
                    .build());
            // ------------------- CATEGORY  -------------------------
            log.info("Preloading categories into database");
            Category restaurant = categoryRepository.save(Category.builder()
                    .nameFr("Restauration")
                    .nameEn("Restaurant")
                    .build());
            Category leisure = categoryRepository.save(Category.builder()
                    .nameFr("Loisir")
                    .nameEn("Leisure")
                    .build());
            Category health = categoryRepository.save(Category.builder()
                    .nameFr("Santé")
                    .nameEn("Health")
                    .build());

            Category shop =categoryRepository.save(Category.builder()
                    .nameFr("Commerce")
                    .nameEn("Shop")
                    .build());
            // ------------------- subCategories  -------------------------
            // ### Restaurant
            SubCategory fastFood = subCategoryRepository.save(SubCategory.builder()
                    .nameFr("Fast-Food")
                    .nameEn("FastFood")
                    .category(restaurant)
            .build());
            SubCategory italianFood = subCategoryRepository.save(SubCategory.builder()
                    .nameFr("Italien")
                    .nameEn("Italian")
                    .category(restaurant)
            .build());
            SubCategory montrealFood = subCategoryRepository.save(SubCategory.builder()
                    .nameFr("Montrealais")
                    .nameEn("Montreal style")
                    .category(restaurant)
            .build());
            SubCategory frenchFood = subCategoryRepository.save(SubCategory.builder()
                    .nameFr("Français")
                    .nameEn("French")
                    .category(restaurant)
            .build());
            SubCategory orientalFood = subCategoryRepository.save(SubCategory.builder()
                    .nameFr("Oriental")
                    .nameEn("Oriental")
                    .category(restaurant)
            .build());
            // ### Shop
            SubCategory clothesShop = subCategoryRepository.save(SubCategory.builder()
                    .nameFr("Vetements")
                    .nameEn("Clothing")
                    .category(shop)
                    .build()
            );
              SubCategory gameShop = subCategoryRepository.save(SubCategory.builder()
                            .nameFr("Jouet")
                            .nameEn("Games")
                            .category(shop)
                            .build()
                    );
              SubCategory hairdressShop = subCategoryRepository.save(SubCategory.builder()
                            .nameFr("Coiffeur")
                            .nameEn("Hairdresse")
                            .category(shop)
                            .build()
                    );


            // ------------------- POI - RESTAURANT -------------------------
            log.info("Preloading districts into database");
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Chez Boss & Fils")
                    .address("3610 Rue Wellington, Verdun")
                    .uri("chezbossetfils.ca")
                    .category(restaurant)
                    .subCategories(Arrays.asList(montrealFood))
                    .phoneNumber("(514)508-3457")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Restaurant Wellington")
                    .address("3629 Rue Wellington, Verdun")
                    .uri("restaurantwellington.com")
                    .category(restaurant)
                    .subCategories(Arrays.asList(montrealFood,fastFood))
                    .phoneNumber("(514)419-1646")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Les Street Monkeys")
                    .address("3625 Rue Wellington, Verdun")
                    .uri("streetmonkeys.ca")
                    .category(restaurant)
                    .subCategories(Arrays.asList(frenchFood))
                    .phoneNumber("(514)768-1818")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Les Street Monkeys")
                    .address("3625 Rue Wellington, Verdun")
                    .uri("streetmonkeys.ca")
                    .category(restaurant)
                    .subCategories(Arrays.asList(fastFood))
                    .phoneNumber("(514)768-1818")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Rita")
                    .address("3681 Rue Wellington, Verdun")
                    .category(restaurant)
                    .subCategories(Arrays.asList(italianFood))
                    .phoneNumber("(514)768-1818")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Le grill J''m la Frite")
                    .address("350 Rue Strathmore, Verdun")
                    .uri("jmlafrite.com")
                    .category(restaurant)
                    .subCategories(Arrays.asList(frenchFood))
                    .phoneNumber("(514)769-3132")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Poutine LaFleur")
                    .address("3665 Rue Wellington, Verdun")
                    .category(restaurant)
                    .subCategories(Arrays.asList(montrealFood, fastFood))
                    .phoneNumber("(514)658-9071")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Parisa")
                    .address("4123 Rue de Verdun, Verdun")
                    .uri("restaurantparisa.ca")
                    .category(restaurant)
                    .subCategories(Arrays.asList(fastFood))
                    .phoneNumber("(514)768-7777")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Pigor")
                    .address("3780 Rue Wellington, Verdun")
                    .category(restaurant)
                    .subCategories(Arrays.asList(italianFood))
                    .phoneNumber("(514)907-0816")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("La Queue de Poisson")
                    .address("3779 Rue Wellington, Verdun")
                    .category(restaurant)
                    .subCategories(Arrays.asList(montrealFood))
                    .phoneNumber("(514)507-8245")
                    .district(verdun)
                    .build());
            pointOfInterestRepository.save(PointOfInterest.builder()
                    .name("Delhi Express")
                    .address("3876 Rue Wellington, Verdun")
                    .uri("nouveau-delhi.com")
                    .category(restaurant)
                    .subCategories(Arrays.asList(montrealFood))
                    .phoneNumber("(514)767-6964")
                    .district(verdun)
                    .build());


        };
    }
}
