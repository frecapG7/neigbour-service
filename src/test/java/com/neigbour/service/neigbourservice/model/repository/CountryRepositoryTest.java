package com.neigbour.service.neigbourservice.model.repository;

import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.apache.commons.collections4.IteratorUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CountryRepository countryRepository;

    @Test
    public void should_insert_canada(){
        Country result = countryRepository.save(Country
                .builder()
                .nameEn(TestConstants.CANADA.getNameEn())
                .nameFr(TestConstants.CANADA.getNameFr())
                .build());

        Assert.assertNotNull(result.getId());
        Assert.assertEquals(result.getNameEn(), TestConstants.CANADA.getNameEn());
        Assert.assertEquals(result.getNameFr(), TestConstants.CANADA.getNameFr());

    }

    @Test
    public void should_find_country_by_id(){
        Long id = (Long) testEntityManager.persistAndGetId(Country
                .builder()
                .nameEn("test")
                .nameFr("test")
                .build());

        Optional<Country> result = countryRepository.findById(id);
        Assert.assertEquals(true, result.isPresent());
        Assert.assertEquals("test" , result.get().getNameFr());
        Assert.assertEquals("test" , result.get().getNameEn());
    }






}
