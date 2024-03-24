package org.library.service;

import org.junit.jupiter.api.*;
import org.library.db.ConnectionFactory;
import org.library.dto.PublisherDTO;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

class PublisherServiceTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    PublisherService publisherService;
    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        publisherService = new PublisherService();
    }

    @Test
    void getById() {
        long id = 1000;
        PublisherDTO expectedPublisher = new PublisherDTO();
        expectedPublisher.setId(id);
        expectedPublisher.setName("Test publisher");

        publisherService.save(expectedPublisher);
        PublisherDTO actualPublisher = publisherService.getById(id);

        assertEquals(expectedPublisher, actualPublisher);

        publisherService.deleteById(1000);
    }

    @Test
    void save() {
        long id = 1000;
        PublisherDTO expectedPublisher = new PublisherDTO();
        expectedPublisher.setId(id);
        expectedPublisher.setName("Test publisher");

        assertTrue(publisherService.save(expectedPublisher));

        publisherService.deleteById(1000);
    }

    @Test
    void deleteById() {
        long id = 1000;
        PublisherDTO expectedPublisher = new PublisherDTO();
        expectedPublisher.setId(id);
        expectedPublisher.setName("Test publisher");
        publisherService.save(expectedPublisher);

        assertTrue(publisherService.deleteById(1000));
    }

    @Test
    void updateById() {
        long id = 1000;
        PublisherDTO expectedPublisher = new PublisherDTO();
        expectedPublisher.setId(id);
        expectedPublisher.setName("Test publisher");

        publisherService.save(expectedPublisher);
        publisherService.updateById(1000, "NewName test publisher");
        PublisherDTO actualPublisher = publisherService.getById(id);

        assertEquals("NewName test publisher", actualPublisher.getName());

        publisherService.deleteById(1000);
    }
}