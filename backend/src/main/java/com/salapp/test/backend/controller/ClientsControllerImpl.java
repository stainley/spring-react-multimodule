package com.salapp.test.backend.controller;

import com.salapp.test.backend.model.Client;
import com.salapp.test.backend.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientsControllerImpl implements ClientsController {
    private static final Logger logger = LoggerFactory.getLogger(ClientsControllerImpl.class);

    private final ClientRepository clientRepository;

    public ClientsControllerImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public ResponseEntity<Client> getClient(Long id) {
        Optional<Client> result = clientRepository.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result.get());
    }

    @Override
    public ResponseEntity<Client> createClient(Client client) throws URISyntaxException {
        logger.info("To be created client {} with email {}", client.getName(), client.getEmail());
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
    }

    @Override
    public ResponseEntity<Client> updateClient(Long id, Client client) {
        Client currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());

        currentClient = clientRepository.save(client);
        return ResponseEntity.ok(currentClient);
    }

    @Override
    public ResponseEntity<Client> deleteClient(Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
