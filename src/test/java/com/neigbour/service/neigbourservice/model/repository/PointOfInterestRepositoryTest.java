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

import java.awt.*;
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

    private PointOfInterest testPointOfInterest = PointOfInterest
            .builder()
            .name(TestConstants.PARISA.getName())
            .phoneNumber(TestConstants.PARISA.getPhoneNumber())
            .address(TestConstants.PARISA.getAddress())
            .district(TestConstants.VERDUN)
            .category(TestConstants.RESTAURANT)
            .subCategories(Arrays.asList(TestConstants.ITALIANFOOD))
            .build();

    @Test
    public void should_find_poi_by_id(){
        Long id = (Long) this.testEntityManager.persistAndGetId(
               testPointOfInterest );
        Optional<PointOfInterest> result = pointOfInterestRepository.findById(id);

        Assert.assertEquals(true, result.isPresent());
        Assert.assertEquals(testPointOfInterest.getName(), result.get().getName());

    }

    @Test
    public void should_save_a_poi(){
        PointOfInterest result = pointOfInterestRepository.save(testPointOfInterest);

        Assert.assertEquals(testPointOfInterest.getName(), result.getName());
    }

    @Test
    public void should_delete_a_poi(){
        Long id = this.testEntityManager.persistAndGetId(testPointOfInterest, Long.class);

        pointOfInterestRepository.deleteById(id);

        Assert.assertNull(this.testEntityManager.find(PointOfInterest.class, id));
    }



}
