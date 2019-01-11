package com.neigbour.service.neigbourservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neigbour.service.neigbourservice.controller.assembler.CityResourceAssembler;
import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.repository.CityRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CityController.class, secure = false)
@ComponentScan(basePackages = "com.neigbour.service.neigbourservice.controller.assembler")
public class CityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CityRepository cityRepository;


    @Test
    public void should_return_montreal() throws Exception{

        City expected = TestConstants.MONTREAL;
        List<District> districts = new ArrayList<>();
        districts.add(TestConstants.VERDUN);
        districts.add(TestConstants.STHENRI);
        expected.setDistrictList(districts);
        Mockito.when(cityRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/cities/{id}", new Long(11353153))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameFr", Matchers.is(TestConstants.MONTREAL.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameEn", Matchers.is(TestConstants.MONTREAL.getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country.nameFr", Matchers.is(TestConstants.CANADA.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country.nameEn", Matchers.is(TestConstants.CANADA.getNameEn())));
    }

    @Test
    public void should_save_montreal() throws Exception{

        Mockito.when(cityRepository.save(Mockito.any())).thenReturn(TestConstants.MONTREAL);

        ObjectMapper objectMapper = new ObjectMapper();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/neigbour/api/cities")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TestConstants.MONTREAL))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    public void should_return_montreal_district_list() throws Exception{
        City city = TestConstants.MONTREAL;
        List<District> districtList = new ArrayList<>();
        districtList.add(TestConstants.STHENRI);
        districtList.add(TestConstants.VERDUN);
        city.setDistrictList(districtList);

        Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.of(city));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/cities/{id}/districts", new Long(11353153))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.districtList.[0].nameFr", Matchers.is(TestConstants.STHENRI.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.districtList.[0].nameEn", Matchers.is(TestConstants.STHENRI.getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.districtList.[0].city.nameEn", Matchers.is(TestConstants.MONTREAL.getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.districtList.[0].city.nameFr", Matchers.is(TestConstants.MONTREAL.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.districtList.[1].nameFr", Matchers.is(TestConstants.VERDUN.getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.districtList.[1].nameEn", Matchers.is(TestConstants.VERDUN.getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.districtList.[1].city.nameEn", Matchers.is(TestConstants.MONTREAL.getNameEn())))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.districtList.[1].city.nameFr", Matchers.is(TestConstants.MONTREAL.getNameFr())));

    }

    @Test
    public void should_return_null_for_city_not_found() throws Exception{
        Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/cities/{id}/district", new Long(11353153))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
