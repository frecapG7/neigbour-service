package com.neigbour.service.neigbourservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterestCategory;
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
public class PointOfInterestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PointOfInterestRepository pointOfInterestRepository;
    @MockBean
    DistrictRepository districtRepository;

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


//    @Test
//    public void should_return_list_poi_by_category() throws Exception{
//        List<PointOfInterest> restaurantList = new ArrayList<>();
//        restaurantList.add(TestConstants.PARISA);
//        restaurantList.add(TestConstants.AKA_FUJI);
//        Mockito.when(pointOfInterestRepository.findByCategory(PointOfInterestCategory.RESTAURANT))
//                .thenReturn(restaurantList);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "/neigbour/api/pois/category/{category}", PointOfInterestCategory.RESTAURANT.getId()).accept(
//                        MediaType.APPLICATION_JSON);
//        mockMvc.perform(requestBuilder)
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", Matchers.is(TestConstants.PARISA.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].address", Matchers.is(TestConstants.PARISA.getAddress())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber", Matchers.is(TestConstants.PARISA.getPhoneNumber())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].category.id", Matchers.is(TestConstants.PARISA.getCategory().getId())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].category.nameFr", Matchers.is(TestConstants.PARISA.getCategory().getNameFr())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].category.nameEn", Matchers.is(TestConstants.PARISA.getCategory().getNameEn())))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].uri", Matchers.is(TestConstants.PARISA.getUri())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].district.nameFr", Matchers.is(TestConstants.PARISA.getDistrict().getNameFr())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].district.nameEn", Matchers.is(TestConstants.PARISA.getDistrict().getNameEn())))
//                // 2 - AKA FUJI
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name", Matchers.is(TestConstants.AKA_FUJI.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].address", Matchers.is(TestConstants.AKA_FUJI.getAddress())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].phoneNumber", Matchers.is(TestConstants.AKA_FUJI.getPhoneNumber())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].category.id", Matchers.is(TestConstants.AKA_FUJI.getCategory().getId())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].category.nameFr", Matchers.is(TestConstants.AKA_FUJI.getCategory().getNameFr())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].category.nameEn", Matchers.is(TestConstants.AKA_FUJI.getCategory().getNameEn())))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].uri", Matchers.is(TestConstants.AKA_FUJI.getUri())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].district.nameFr", Matchers.is(TestConstants.AKA_FUJI.getDistrict().getNameFr())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].district.nameEn", Matchers.is(TestConstants.AKA_FUJI.getDistrict().getNameEn())))
//
//        ;
//    }

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

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/neigbour/api/poi/{id}", "123455")
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
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/neigbour/api/poi/{id}", "123455")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(TestConstants.PARISA))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void should_find_restaurant_by_district() throws Exception{
        List<PointOfInterest> pointOfInterests = new ArrayList<>();

        pointOfInterests.add(TestConstants.PARISA);
        pointOfInterests.add(TestConstants.AKA_FUJI);

        Mockito.when(districtRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestConstants.STHENRI));

        Mockito.when(pointOfInterestRepository.findByDistrict(Mockito.any())).thenReturn(pointOfInterests);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/poi/district/{id}", 1224)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", Matchers.is(TestConstants.PARISA.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].address", Matchers.is(TestConstants.PARISA.getAddress())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].phoneNumber", Matchers.is(TestConstants.PARISA.getPhoneNumber())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].category", Matchers.is(TestConstants.PARISA.getCategory().getNameFr())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].uri", Matchers.is(TestConstants.PARISA.getUri())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].district.nameFr", Matchers.is(TestConstants.PARISA.getDistrict().getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].district.nameEn", Matchers.is(TestConstants.PARISA.getDistrict().getNameEn())))
                // 2 - AKA FUJI
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name", Matchers.is(TestConstants.AKA_FUJI.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].address", Matchers.is(TestConstants.AKA_FUJI.getAddress())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].phoneNumber", Matchers.is(TestConstants.AKA_FUJI.getPhoneNumber())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].category", Matchers.is(TestConstants.AKA_FUJI.getCategory().getNameFr())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].uri", Matchers.is(TestConstants.AKA_FUJI.getUri())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].district.nameFr", Matchers.is(TestConstants.AKA_FUJI.getDistrict().getNameFr())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].district.nameEn", Matchers.is(TestConstants.AKA_FUJI.getDistrict().getNameEn())));

    }

}
