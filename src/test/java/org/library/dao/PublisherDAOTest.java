package org.library.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.db.TestConnectionFactory;
import org.library.entities.Publisher;
import org.library.mappers.PublisherMapper;
import org.library.mappers.PublisherMapperImpl;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

class PublisherDAOTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    ).withInitScript("databaseCreation.sql");

    PublisherDAO publisherDAO;

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
        TestConnectionFactory connectionProvider = new TestConnectionFactory(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword()
        );
        publisherDAO = new PublisherDAO(connectionProvider.getConnection());

        publisherDAO.deleteById(1000);
    }

    @Test
    void findByStrictId_SavePublisherEntity_ReturnPublisherEntity_ConvertToPublisherDTO() {
        long id = 1000;
        Publisher expectedPublisher = new Publisher();
        expectedPublisher.setId(1000);
        expectedPublisher.setName("Test publisher");

        publisherDAO.save(expectedPublisher);
        Publisher actualPublisher = publisherDAO.findByIdStrict(1000);

        PublisherMapper publisherMapper = new PublisherMapperImpl();

        assertEquals(publisherMapper.toPublisherDTO(expectedPublisher).toString(),
                publisherMapper.toPublisherDTO(actualPublisher).toString());
    }

    @Test
    void savePublisher_AssertTrueIfSaved() {
        long id = 1000;
        Publisher expectedPublisher = new Publisher();
        expectedPublisher.setId(1000);
        expectedPublisher.setName("Test publisher");

        assertTrue(publisherDAO.save(expectedPublisher));
    }

    @Test
    void deleteByIdPublisher_AssertTrueIfDeleted() {
        long id = 1000;
        Publisher expectedPublisher = new Publisher();
        expectedPublisher.setId(1000);
        expectedPublisher.setName("Test publisher");

        publisherDAO.save(expectedPublisher);

        assertTrue(publisherDAO.deleteById(1000));
    }

    @Test
    void updateByIdPublisher_ChangeName_ReturnPublisherWithNewName() {
        long id = 1000;
        Publisher expectedPublisher = new Publisher();
        expectedPublisher.setId(1000);
        expectedPublisher.setName("Test publisher");

        publisherDAO.save(expectedPublisher);
        publisherDAO.updateById(1000,"New name");
        Publisher actualPublisher = publisherDAO.findByIdStrict(1000);

        expectedPublisher.setName("New name");

        PublisherMapper publisherMapper = new PublisherMapperImpl();

        assertEquals(publisherMapper.toPublisherDTO(expectedPublisher).toString(),
                publisherMapper.toPublisherDTO(actualPublisher).toString());
    }
}