package com.neigbour.service.neigbourservice.model.repository;

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

        testEntityManager.persist(TestConstants.RITA);
        List<PointOfInterest> results = pointOfInterestRepository.findByCategory(PointOfInterest.Category.RESTAURANT);

        Assert.assertNotNull(results);

    }

    @Test
    public void should_find_poi_based_on_district(){

    }


}
