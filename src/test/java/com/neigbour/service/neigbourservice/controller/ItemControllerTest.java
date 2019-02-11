package com.neigbour.service.neigbourservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class, secure = false)
@ComponentScan(basePackages = "com.neigbour.service.neigbourservice.controller.assembler")
public class ItemControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    PointOfInterestRepository pointOfInterestRepository;

    ObjectMapper objectMapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();


    }

    @Test
    public void should_return_list_of_items() throws Exception{
        Mockito.when(pointOfInterestRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TestConstants.AKA_FUJI));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/neigbour/api/pois/{poisId}/items", new Long(1351))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print());

    }



}
