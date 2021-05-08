package com.salapp.test.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salapp.test.backend.model.Client;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ClientControllerIT {

    private Client client;

    @Autowired
    private ClientsController clientsController;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.clientsController).build();
        client = new Client();
        client.setName("test");
        client.setEmail("test@mail.com");
    }

    @Test
    public void shouldCreateClient() throws Exception {

        MvcResult result = mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andReturn();

        String resultClient = result.getResponse().getContentAsString();

        assertNotNull(resultClient);
    }

    @Test
    public void shouldSearchClientCreated() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/clients/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is("test")))
                .andReturn();

        assertNotNull(mvcResult);
    }

    @Test
    public void shouldNotFoundClient() throws Exception {
        mockMvc.perform(get("/clients/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void shouldDeleteClientById() throws Exception {
        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andReturn();

        mockMvc.perform(delete("/clients/{id}", 2))
                .andExpect(status().isOk());

        mockMvc.perform(get("/clients/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
