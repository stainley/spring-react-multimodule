package com.salapp.test.backend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.salapp.test.backend.model.Client;
import com.salapp.test.backend.repositories.ClientRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateClientSuccessfully() throws Exception {
        String expectedName = "root";
        Client client = new Client();
        client.setName(expectedName);
        client.setEmail("root@mail.com");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Matchers.is(expectedName)));
    }
}
