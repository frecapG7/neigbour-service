package com.neigbour.service.neigbourservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.repository.DistrictRepository;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PointOfInterestController.class, secure = false)
@ComponentScan(basePackages = "com.neigbour.service.neigbourservice.controller.assembler")
public class PointOfInterestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PointOfInterestRepository pointOfInterestRepository;

    ObjectMapper objectMapper;

    @Before
    public void setUp(){
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void sould_return_poi_by_id() throws Exception{
        Mockito.when(pointOfInterestRepository.findById(Mockito.any())).thenReturn(Optional.of(TestConstants.RITA));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/neigbour/api/pois/{id}", "121354541")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(TestConstants.RITA.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.is(TestConstants.RITA.getAddress())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", Matchers.is(TestConstants.RITA.getPhoneNumber())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].category", Matchers.is(TestConstants.PARISA.getCategory().getNameFr())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].uri", Matchers.is(TestConstants.PARISA.getUri())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.district.nameFr", Matchers.is(TestConstants.RITA.getDistrict().getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.district.nameEn", Matchers.is(TestConstants.RITA.getDistrict().getNameEn())));
    }



    @Test
    public void should_save_a_restaurant() throws Exception{
        Mockito.when(pointOfInterestRepository.save(Mockito.any())).thenReturn(TestConstants.PARISA);

        System.out.println(objectMapper.writeValueAsString(TestConstants.PARISA));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/neigbour/api/poi")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TestConstants.PARISA))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void should_return_exception_cause_no_district() throws Exception{
        //TODO
    }

    @Test
    public void should_update_a_poi() throws Exception{
        Mockito.when(pointOfInterestRepository.findById(Mockito.any())).thenReturn(Optional.of(TestConstants.PARISA));
        Mockito.when(pointOfInterestRepository.save(Mockito.any())).thenReturn(TestConstants.PARISA);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/neigbour/api/pois/{id}", "123455")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TestConstants.PARISA))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void should_return_not_found_during_update() throws Exception{
        Mockito.when(pointOfInterestRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/neigbour/api/pois/{id}", "123455")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TestConstants.PARISA))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


}
