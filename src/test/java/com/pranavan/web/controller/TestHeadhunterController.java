package com.pranavan.web.controller;

import com.pranavan.web.model.Headhunter;
import com.pranavan.web.service.HeadhunterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by pranavan on 7/15/18.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = HeadhunterController.class, secure = false)
public class TestHeadhunterController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HeadhunterService headhunterService;
    private String headhunterString="{\"id\":1,\"name\":\"ABC\",\"active\":true}";
    private String headhunterUpdateString="{\"id\":1,\"name\":\"ABCD\",\"active\":true}";

    private Headhunter headhunter;
    private Headhunter headhunterUpdate;
    private List<Headhunter> headhunterList=new ArrayList<>();
    @Before
    public void setUp() {

        headhunter= new Headhunter();
        headhunter.setId(1L);
        headhunter.setActive(true);
        headhunter.setName("ABC");

        headhunterList.add(headhunter);
        Mockito.when(
                headhunterService.getHeadhunters()).thenReturn(headhunterList);




    }

    @Test
    public void testCreateHeadhunter() throws Exception {

        Mockito.when(
                headhunterService.saveHeadhunter(
                        Mockito.any(Headhunter.class))).thenReturn(headhunter);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/headhunter")
                .accept(MediaType.APPLICATION_JSON).content(headhunterString)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());


    }

    @Test
    public void testUpdateHeadhunter() throws Exception {
        headhunterUpdate=headhunter;
        headhunterUpdate.setName("ABCD");
        headhunterList.remove(headhunter);
        headhunterList.add(headhunterUpdate);
        Mockito.when(
                headhunterService.updateHeadhunter(
                        Mockito.any(Headhunter.class))).thenReturn(headhunterUpdate);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/headhunter/1")
                .accept(MediaType.APPLICATION_JSON).content(headhunterUpdateString)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());


    }

    @Test
    public void testDeleteadhunter() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/headhunter/1")
                .accept(MediaType.APPLICATION_JSON).content(headhunterUpdateString)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());



    }


}
