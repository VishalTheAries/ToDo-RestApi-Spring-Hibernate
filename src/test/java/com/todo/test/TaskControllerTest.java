package com.todo.test;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.ToDoApplication;
import com.todo.api.TaskController;
import com.todo.models.Task;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ToDoApplication.class)
public class TaskControllerTest {

	
	private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/todo/task//?[0-9]+";
	
    @InjectMocks
    TaskController controller;

    @Autowired
    WebApplicationContext context;
    
    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }	
    
    @Test
    public void testCRUDoperation() throws Exception {
        Task r1 = mockTask("shouldCreate");
        byte[] r1Json = toJson(r1);

        //CREATE
        MvcResult result = mvc.perform(post("/todo/task")
                .content(r1Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        
        long id = getResourceIdFromUrl(result.getResponse().getRedirectedUrl());

        //RETRIEVE
        mvc.perform(get("/todo/task/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) id)))
                .andExpect(jsonPath("$.description", is(r1.getDescription())));
        
        Task r2 = mockTask("shouldUpdate");
        r2.setId(id);
        byte[] r2Json = toJson(r2);

        //UPDATE
        result = mvc.perform(put("/todo/task/" + id)
                .content(r2Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        //DELETE
        mvc.perform(delete("/todo/task/" + id))
        	.andExpect(status().isNoContent());
    }

    @Test
    public void testGet() throws Exception {
    	
        //RETRIEVE
        mvc.perform(get("/todo/task")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }
    private Task mockTask(String func) {
        Task r = new Task();
        r.setDescription(func+":: mock description");
        r.setDate(LocalDate.of(2014, Month.JANUARY, 1));
        return r;
    }
    private long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }	
}
