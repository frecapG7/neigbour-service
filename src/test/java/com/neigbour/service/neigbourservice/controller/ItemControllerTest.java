package com.neigbour.service.neigbourservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.neigbour.service.neigbourservice.model.entity.Item;
import com.neigbour.service.neigbourservice.model.repository.ItemRepository;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import com.neigbour.service.neigbourservice.util.TestConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class, secure = false)
@ComponentScan(basePackages = "com.neigbour.service.neigbourservice.controller.assembler")
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PointOfInterestRepository pointOfInterestRepository;
    @MockBean
    ItemRepository itemRepository;

    ObjectMapper objectMapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();


        TestConstants
                .AKA_FUJI.setItems(Arrays.asList(Item.builder()
                        .nameFr("Menu1")
                        .nameEn("Menu1")
                        .description("AKA FUJI Menu")
                        .price(new BigDecimal(4638))
                        .pointOfInterest(TestConstants.AKA_FUJI)
                        .build(),
                Item.builder()
                        .nameFr("Menu2")
                        .nameEn("Menu2")
                        .description("AKA FUJI Menu")
                        .price(new BigDecimal(123))
                        .pointOfInterest(TestConstants.AKA_FUJI)
                        .build())
        );
    }

    @Test
    public void should_return_list_of_items() throws Exception{
        Mockito.when(pointOfInterestRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestConstants.AKA_FUJI));


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/pois/{poisId}/items", new Long(1351))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void should_return_exception_cause_no_poi() throws Exception{
        Mockito.when(pointOfInterestRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/pois/{poisId}/items", new Long(1351))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

//    TODO Mock void method
//    @Test
//    public void should_delete_an_item() throws Exception{
//
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/items/{id}", new Long(3554663));
//
//        mockMvc.perform(requestBuilder)
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//    }

    @Test
    public void should_insert_an_item() throws Exception{
        Mockito.when(pointOfInterestRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestConstants.AKA_FUJI));

        Item testItem = Item.builder()
                .id(new Long(2153645))
                .nameFr("Menu1")
                .nameEn("Menu1")
                .description("AKA FUJI Menu")
                .price(new BigDecimal(254))
                .pointOfInterest(TestConstants.AKA_FUJI)
                .build();
        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(testItem);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/neigbour/api/pois/{id}/items", new Long(265))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testItem));
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }








}
