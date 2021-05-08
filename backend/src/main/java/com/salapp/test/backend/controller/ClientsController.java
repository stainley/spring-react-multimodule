package com.salapp.test.backend.controller;

import com.salapp.test.backend.model.Client;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

public interface ClientsController {

    @GetMapping
    List<Client> getClients();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Client> getClient(@PathVariable Long id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Client> createClient(@RequestBody Client client) throws URISyntaxException;

    @PutMapping("/{id}")
    ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client);

    @DeleteMapping("{id}")
    ResponseEntity<Client> deleteClient(@PathVariable Long id);
}
