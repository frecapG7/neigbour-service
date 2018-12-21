package com.neigbour.service.neigbourservice.model.repository;

import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    public void should_find_list_of_poi_based_on_category(){


        PointOfInterest poi1 = PointOfInterest
                .builder()
                .name("Rita")
                .address("5453 rue du cannard")
                .category(PointOfInterest.Category.RESTAURANT)
                .build();

        testEntityManager.persist(poi1);


        List<PointOfInterest> results = pointOfInterestRepository.findByCategory(PointOfInterest.Category.RESTAURANT);

        Assert.assertNotNull(results);

    }


}
