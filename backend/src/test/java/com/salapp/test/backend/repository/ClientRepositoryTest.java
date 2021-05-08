package com.salapp.test.backend.repository;

import com.salapp.test.backend.model.Client;
import com.salapp.test.backend.repositories.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Transactional(propagation = Propagation.REQUIRED)
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void shouldCreateClient() {
        Client client = new Client();
        client.setName("root");
        client.setEmail("root@mail.com");

        Client clientReturned = clientRepository.save(client);
        Optional<Client> result = clientRepository.findById(clientReturned.getId());

        assertEquals("root", result.<Object>map(Client::getName).orElse(null));
    }

    @Nested
    @DisplayName(value = "Batch of selects")
    class TestSelection {

        @Test
        public void shouldReturnClientById() {
            Client client = new Client();
            client.setName("test1");
            client.setEmail("test@mail.com");
            entityManager.persist(client);

            Optional<Client> result = clientRepository.findById(2L);

            assertEquals("test1", result.<Object>map(Client::getName).orElse(null));
        }
    }

}
