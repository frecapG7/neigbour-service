package com.neigbour.service.neigbourservice.controller;

import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PointOfInterestController.class, secure = false)
public class PointOfInterestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PointOfInterestRepository pointOfInterestRepository;

    List<PointOfInterest> restaurantList = new ArrayList<>();
    PointOfInterest restaurant1;
    PointOfInterest restaurant2;
    PointOfInterest shop1;

    @Before
    public void setUp(){
        buildTestData();
    }

    @Test
    public void should_return_list_poi_by_category() throws Exception{
        Mockito.when(pointOfInterestRepository.findByCategory(PointOfInterest.Category.RESTAURANT))
                .thenReturn(restaurantList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/neigbour/api/poi/category/{category}", PointOfInterest.Category.RESTAURANT.getId()).accept(
                        MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    private void buildTestData(){
         restaurant1 = PointOfInterest.builder()
                .id(new Long(5465413))
                .name("restaurant1")
                .address("restaurant1")
                .category(PointOfInterest.Category.RESTAURANT)
                .build();
         restaurant2 = PointOfInterest.builder()
                .id(new Long(5465414))
                .name("restaurant2")
                .address("restaurant2")
                .category(PointOfInterest.Category.RESTAURANT)
                .build();
         shop1 = PointOfInterest.builder()
                .id(new Long(5465415))
                .name("shop1")
                .address("shop1")
                .category(PointOfInterest.Category.SHOP)
                .build();
         restaurantList.add(restaurant1);
         restaurantList.add(restaurant2);
    }
}
