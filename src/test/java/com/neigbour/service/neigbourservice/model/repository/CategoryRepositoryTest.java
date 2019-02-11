package com.neigbour.service.neigbourservice.model.repository;


import com.neigbour.service.neigbourservice.model.entity.Category;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {


    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CategoryRepository categoryRepository;


    @Before
    public void setUp(){

    }



    @Test
    public void should_insert_a_category(){
        Category result = categoryRepository.save(
                Category
                        .builder()
                        .nameFr(TestConstants.RESTAURANT.getNameFr())
                        .nameEn(TestConstants.RESTAURANT.getNameEn())
                        .subCategories(TestConstants.RESTAURANT.getSubCategories())
                        .build()
        );


        Assert.assertNotNull(result.getId());
        Assert.assertEquals(TestConstants.RESTAURANT.getNameEn(), result.getNameEn());
        Assert.assertEquals(TestConstants.RESTAURANT.getNameFr(), result.getNameFr());
        Assert.assertEquals(TestConstants.RESTAURANT.getSubCategories(), result.getSubCategories());
    }

    @Test
    public void should_find_a_category(){
        Long id = this.testEntityManager.persistAndGetId(
                Category
                        .builder()
                        .nameEn(TestConstants.RESTAURANT.getNameEn())
                .nameFr(TestConstants.RESTAURANT.getNameFr())
                .subCategories(TestConstants.RESTAURANT.getSubCategories())
                .build(),
                Long.class
        );

        Optional<Category> result = categoryRepository.findById(id);
        Assert.assertEquals(true, result.isPresent());
        Assert.assertEquals(TestConstants.RESTAURANT.getNameFr(), result.get().getNameFr());
        Assert.assertEquals(TestConstants.RESTAURANT.getNameEn(), result.get().getNameEn());
        Assert.assertEquals(TestConstants.RESTAURANT.getSubCategories(), result.get().getSubCategories());
    }

    @Test
    public void should_delete_a_category(){
        Long id = this.testEntityManager.persistAndGetId(
                Category
                        .builder()
                        .nameEn(TestConstants.RESTAURANT.getNameEn())
                        .nameFr(TestConstants.RESTAURANT.getNameFr())
                        .subCategories(TestConstants.RESTAURANT.getSubCategories())
                        .build(),
                Long.class
        );

        categoryRepository.deleteById(id);
    }

    //TODO: test update


}
