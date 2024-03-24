package org.library.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.dto.BookDTO;
import org.mockito.Mockito;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class BookServiceTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    BookService bookService;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp(){ bookService = new BookService();}

    @Test
    void save() {
        long id = 1000;
        BookDTO expectedBook = new BookDTO();
        expectedBook.setId(1000);
        expectedBook.setTitle("Test title");
        expectedBook.setPublisher("1");
        expectedBook.setAuthors(Arrays.asList("1","2"));

        System.out.println(expectedBook.toString());

        assertTrue(bookService.save(expectedBook));

        bookService.deleteById(1000);

    }

    @Test
    void deleteById() {
        long id = 1000;
        BookDTO expectedBook = new BookDTO();
        expectedBook.setId(1000);
        expectedBook.setTitle("Test title");
        expectedBook.setPublisher("1");
        expectedBook.setAuthors(Arrays.asList("1","2"));

        bookService.save(expectedBook);

        assertTrue(bookService.deleteById(1000));

    }

    @Test
    void updateById() {
        long id = 1000;
        BookDTO expectedBook = new BookDTO();
        expectedBook.setId(1000);
        expectedBook.setTitle("Test title");
        expectedBook.setPublisher("1");
        expectedBook.setAuthors(Arrays.asList("1","2"));

        bookService.save(expectedBook);
        bookService.updateById(1000, "NewName test book");

        BookDTO acctualBook = bookService.getById(1000);

        assertEquals("NewName test book", acctualBook.getTitle());;

        assertTrue(bookService.deleteById(1000));
    }
}