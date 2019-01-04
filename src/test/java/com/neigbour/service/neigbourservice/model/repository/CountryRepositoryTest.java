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
        Country result = countryRepository.save(TestConstants.CANADA);

        Assert.assertNotNull(result.getId());
        Assert.assertEquals(result.getNameEn(), TestConstants.CANADA.getNameEn());
        Assert.assertEquals(result.getNameFr(), TestConstants.CANADA.getNameFr());

    }

    @Test
    public void should_find_canada(){
        Country expected = testEntityManager.persist(TestConstants.CANADA);

        Optional<Country> result = countryRepository.findById(expected.getId());
        Assert.assertEquals(true, result.isPresent());
        Assert.assertEquals(expected.getNameFr() , result.get().getNameFr());
        Assert.assertEquals(expected.getNameEn() , result.get().getNameEn());
    }

    @Test
    public void should_find_two_countries(){
        testEntityManager.persist(TestConstants.CANADA);
        testEntityManager.persist(TestConstants.FRANCE);

        List<Country> results = IteratorUtils.toList(countryRepository.findAll().iterator());
        Assert.assertEquals(2, results.size());
    }


}
