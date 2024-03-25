package org.library.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.db.TestConnectionFactory;
import org.library.entities.Author;
import org.library.mappers.AuthorMapper;
import org.library.mappers.AuthorMapperImpl;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDAOTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    ).withInitScript("databaseCreation.sql");

    AuthorDAO authorDAO;

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
        authorDAO = new AuthorDAO(connectionProvider.getConnection());

        authorDAO.deleteById(1000);
    }

        @Test
    void findByStrictId_SaveAuthorEntity_ReturnAuthorEntity_ConvertToAuthorDTO() {
        long id = 1000;
        Author expectedAuthor = new Author();
        expectedAuthor.setId(1000);
        expectedAuthor.setName("Test author");

        authorDAO.save(expectedAuthor);
        Author actualAuthor = authorDAO.findByIdStrict(1000);

        AuthorMapper authorMapper = new AuthorMapperImpl();

        assertEquals(authorMapper.toAuthorDTO(expectedAuthor).toString(),
                     authorMapper.toAuthorDTO(actualAuthor).toString());
    }

    @Test
    void saveAuthor_AssertTrueIfSaved() {
        long id = 1000;
        Author expectedAuthor = new Author();
        expectedAuthor.setId(1000);
        expectedAuthor.setName("Test author");

        assertTrue(authorDAO.save(expectedAuthor));
    }

    @Test
    void deleteByIdAuthor_AssertTrueIfDeleted() {
        long id = 1000;
        Author expectedAuthor = new Author();
        expectedAuthor.setId(1000);
        expectedAuthor.setName("Test author");

        authorDAO.save(expectedAuthor);

        assertTrue(authorDAO.deleteById(1000));
    }

    @Test
    void updateByIdAuthor_ChangeName_ReturnAuthorWithNewName() {
        long id = 1000;
        Author expectedAuthor = new Author();
        expectedAuthor.setId(1000);
        expectedAuthor.setName("Test author");

        authorDAO.save(expectedAuthor);
        authorDAO.updateById(1000,"New name");
        Author actualAuthor = authorDAO.findByIdStrict(1000);

        expectedAuthor.setName("New name");

        AuthorMapper authorMapper = new AuthorMapperImpl();
        assertEquals(authorMapper.toAuthorDTO(expectedAuthor).toString(),
                authorMapper.toAuthorDTO(actualAuthor).toString());
    }
}