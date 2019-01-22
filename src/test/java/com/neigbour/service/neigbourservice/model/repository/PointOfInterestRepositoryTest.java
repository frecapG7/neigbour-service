package com.neigbour.service.neigbourservice.model.repository;

import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PointOfInterestRepositoryTest {


    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PointOfInterestRepository pointOfInterestRepository;


    @Before
    public void setUp(){

    }

    @Test
    public void should_find_poi_by_id(){
        Long id = (Long) this.testEntityManager.persistAndGetId(
                PointOfInterest
                        .builder()
                        .name("Test")
                .phoneNumber("Test")
                .address("Test")
                .district(TestConstants.VERDUN)
                .subCategories(Arrays.asList(TestConstants.ITALIANFOOD))
                .build());
        Optional<PointOfInterest> result = pointOfInterestRepository.findById(id);

        Assert.assertEquals(true, result.isPresent());
        Assert.assertEquals("Test", result.get().getName());

    }

    @Test
    public void should_find_by_category(){
        testEntityManager.persist(PointOfInterest
                .builder()
                .name("Test")
                .phoneNumber("Test")
                .address("Test")
                .district(TestConstants.VERDUN)
                .subCategories(Arrays.asList(TestConstants.ITALIANFOOD))
                .build());
        List<PointOfInterest> results = pointOfInterestRepository.findByCategory(TestConstants.RESTAURANT);

        Assert.assertEquals(false, results.isEmpty());

    }


}
