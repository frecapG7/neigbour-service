package com.neigbour.service.neigbourservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.repository.CountryRepository;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CountryController.class, secure = false)
public class CountryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CountryRepository countryRepository;

    @Test
    public void should_return_list_of_country() throws Exception{

        List resultSet = new ArrayList();
        resultSet.add(TestConstants.CANADA);
        resultSet.add(TestConstants.FRANCE);

        Mockito.when(countryRepository.findAll()).thenReturn(resultSet);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/country")
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nameFr", Matchers.is(TestConstants.CANADA.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nameEn", Matchers.is(TestConstants.CANADA.getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nameFr", Matchers.is(TestConstants.FRANCE.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nameEn", Matchers.is(TestConstants.FRANCE.getNameEn())));
    }

    @Test
    public void should_return_canada() throws Exception{
        Mockito.when(countryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestConstants.CANADA));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/country/{id}", new Long(11353153))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameFr", Matchers.is(TestConstants.CANADA.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameEn", Matchers.is(TestConstants.CANADA.getNameEn())));
    }

    @Test
    public void should_save_canada() throws Exception{

        Mockito.when(countryRepository.save(Mockito.any())).thenReturn(TestConstants.CANADA);

        ObjectMapper objectMapper = new ObjectMapper();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/neigbour/api/country")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TestConstants.CANADA))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void should_return_city_list() throws Exception{
        Country country = TestConstants.CANADA;

        List<City> cityList = new ArrayList<>();
        cityList.add(TestConstants.MONTREAL);
        cityList.add(TestConstants.TORONTO);
        country.setCityList(cityList);

        Mockito.when(countryRepository.findById(Mockito.any())).thenReturn(Optional.of(country));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/country/{id}/cities", "3456452")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nameEn", Matchers.is(TestConstants.MONTREAL.getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nameFr", Matchers.is(TestConstants.MONTREAL.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nameEn", Matchers.is(TestConstants.TORONTO.getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nameFr", Matchers.is(TestConstants.TORONTO.getNameFr())))
                ;

    }

    /**
     * TODO: evolve into exception ??
     */
    @Test
    public void should_return_null_for_country_not_found() throws Exception{

        Mockito.when(countryRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/country/{id}/cities", "3456452")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }





}
