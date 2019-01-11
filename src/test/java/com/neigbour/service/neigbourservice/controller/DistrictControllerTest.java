package com.neigbour.service.neigbourservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.repository.DistrictRepository;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DistrictController.class, secure = false)
@ComponentScan(basePackages = "com.neigbour.service.neigbourservice.controller.assembler")
public class DistrictControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DistrictRepository districtRepository;


    @Test
    public void should_return_sthenri() throws Exception{

        District expected = TestConstants.STHENRI;
        List<PointOfInterest> pointOfInterests = new ArrayList<>();
        pointOfInterests.add(TestConstants.AKA_FUJI);
        pointOfInterests.add(TestConstants.PARISA);
        pointOfInterests.add(TestConstants.RITA);
        expected.setPointOfInterestList(pointOfInterests);
        Mockito.when(districtRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/districts/{id}", new Long(11353153))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameFr", Matchers.is(TestConstants.STHENRI.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameEn", Matchers.is(TestConstants.STHENRI.getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descriptionFr", Matchers.is(TestConstants.STHENRI.getDescriptionFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descriptionEn", Matchers.is(TestConstants.STHENRI.getDescriptionEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city.nameFr", Matchers.is(TestConstants.STHENRI.getCity().getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city.nameEn", Matchers.is(TestConstants.STHENRI.getCity().getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city.country.nameFr", Matchers.is(TestConstants.STHENRI.getCity().getCountry().getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city.country.nameEn", Matchers.is(TestConstants.STHENRI.getCity().getCountry().getNameEn())));
    }

    @Test
    public void should_save_sthenri() throws Exception{

        Mockito.when(districtRepository.save(Mockito.any())).thenReturn(TestConstants.STHENRI);

        ObjectMapper objectMapper = new ObjectMapper();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/neigbour/api/districts")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TestConstants.STHENRI))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }




}
