package com.neigbour.service.neigbourservice.model.repository;

import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CityRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CityRepository cityRepository;


    @Before
    public void setUp(){
    }

    @Test
    public void should_find_city(){
        Long id = (Long) this.testEntityManager.persistAndGetId(City
                .builder()
                .nameEn("Test")
                .nameFr("Test")
                .country(TestConstants.CANADA)
                .build());
        Optional<City> result = cityRepository.findById(id);

        Assert.assertEquals(true, result.isPresent());
        Assert.assertEquals("Test", result.get().getNameFr());
        Assert.assertEquals("Test", result.get().getNameEn());
        Assert.assertEquals(TestConstants.CANADA, result.get().getCountry());

    }

    @Test
    public void should_save_montreal(){

        City result = cityRepository.save(City
                .builder()
                .nameEn(TestConstants.MONTREAL.getNameEn())
                .nameFr(TestConstants.MONTREAL.getNameFr())
                .country(TestConstants.CANADA)
                .build());

        Assert.assertNotNull(result.getId());
        Assert.assertEquals(TestConstants.MONTREAL.getNameFr(), result.getNameFr());
        Assert.assertEquals(TestConstants.MONTREAL.getNameEn(), result.getNameEn());
        Assert.assertEquals(TestConstants.MONTREAL.getCountry(), result.getCountry());

//        Assert.assertEquals(result, countryRepository.findById(savedCountry.getId()).get().getCityList().get(0));
    }

}
