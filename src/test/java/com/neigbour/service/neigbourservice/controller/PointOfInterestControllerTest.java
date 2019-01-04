package com.neigbour.service.neigbourservice.controller;

import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import com.neigbour.service.neigbourservice.util.TestConstants;
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

    @Test
    public void should_return_list_poi_by_category() throws Exception{
        List<PointOfInterest> restaurantList = new ArrayList<>();
        restaurantList.add(TestConstants.restaurant1);
        restaurantList.add(TestConstants.restaurant2);
        Mockito.when(pointOfInterestRepository.findByCategory(PointOfInterest.Category.RESTAURANT))
                .thenReturn(restaurantList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/neigbour/api/poi/category/{category}", PointOfInterest.Category.RESTAURANT.getId()).accept(
                        MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

}
