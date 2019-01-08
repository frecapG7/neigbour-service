package com.neigbour.service.neigbourservice.model.repository;

import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
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

        City expected = TestConstants.MONTREAL;

        City result = cityRepository.save(expected);

        Assert.assertNotNull(result.getId());
        Assert.assertEquals(expected.getNameFr(), result.getNameFr());
        Assert.assertEquals(expected.getNameEn(), result.getNameEn());
        Assert.assertEquals(expected.getCountry(), result.getCountry());

//        Assert.assertEquals(result, countryRepository.findById(savedCountry.getId()).get().getCityList().get(0));
    }

}