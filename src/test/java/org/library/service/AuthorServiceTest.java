package org.library.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.dto.AuthorDTO;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    AuthorService authorService;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp(){ authorService = new AuthorService();}

    @Test
    void getById() {
        long id = 1000;
        AuthorDTO expectedAuthor = new AuthorDTO();
        expectedAuthor.setId(1000);
        expectedAuthor.setName("Test author");

        authorService.save(expectedAuthor);

        AuthorDTO actualAuthor = authorService.getById(1000);

        assertEquals(expectedAuthor, actualAuthor);

        authorService.deleteById(1000);
    }

    @Test
    void save() {
        long id = 1000;
        AuthorDTO expectedAuthor = new AuthorDTO();
        expectedAuthor.setId(1000);
        expectedAuthor.setName("Test author");

        assertTrue(authorService.save(expectedAuthor));

        authorService.deleteById(1000);
    }

    @Test
    void deleteById() {
        long id = 1000;
        AuthorDTO expectedAuthor = new AuthorDTO();
        expectedAuthor.setId(1000);
        expectedAuthor.setName("Test author");
        authorService.save(expectedAuthor);

        assertTrue(authorService.deleteById(1000));

    }

    @Test
    void updateById() {
        long id = 1000;
        AuthorDTO expectedAuthor = new AuthorDTO();
        expectedAuthor.setId(1000);
        expectedAuthor.setName("Test author");
        authorService.save(expectedAuthor);
        authorService.updateById(1000, "NewName test author");

        AuthorDTO actualAuthor = authorService.getById(1000);

        assertEquals("NewName test author", actualAuthor.getName());

        assertTrue(authorService.deleteById(1000));
    }
}