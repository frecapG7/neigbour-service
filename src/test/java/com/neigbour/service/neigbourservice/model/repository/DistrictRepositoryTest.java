package com.neigbour.service.neigbourservice.model.repository;

import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DistrictRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    DistrictRepository districtRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void should_insert_sthenri() {
        District expected = TestConstants.STHENRI;
        District result = districtRepository.save(expected);

        Assert.assertNotNull(result.getId());
        Assert.assertEquals(expected.getNameFr(), result.getNameFr());
        Assert.assertEquals(expected.getNameEn(), result.getNameEn());
        Assert.assertEquals(expected.getDescriptionFr(), result.getDescriptionFr());
        Assert.assertEquals(expected.getDescriptionEn(), result.getDescriptionEn());
        Assert.assertEquals(expected.getCity(), result.getCity());

    }

    @Test
    public void should_find_sthenri() {
        Long id = (Long) this.testEntityManager.persistAndGetId(District
                .builder()
                .nameFr("TestDistrict")
                .nameEn("TestDistrict")
                .descriptionEn("DescriptionTest")
                .descriptionFr("DescriptionTest")
                .city(TestConstants.MONTREAL)
                .build()
        );
        Optional<District> result = districtRepository.findById(id);

        Assert.assertEquals(true, result.isPresent());
        Assert.assertEquals("TestDistrict", result.get().getNameFr());
        Assert.assertEquals("TestDistrict", result.get().getNameEn());
        Assert.assertEquals("DescriptionTest", result.get().getDescriptionFr());
        Assert.assertEquals("DescriptionTest", result.get().getDescriptionEn());
        Assert.assertEquals(TestConstants.MONTREAL, result.get().getCity());

    }
}