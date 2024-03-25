package org.library.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.db.TestConnectionFactory;
import org.library.entities.Book;
import org.library.entities.Publisher;
import org.library.mappers.BookMapper;
import org.library.mappers.BookMapperImpl;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    ).withInitScript("databaseCreation.sql");

    BookDAO bookDAO;
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
        bookDAO = new BookDAO(connectionProvider.getConnection());
        publisherDAO = new PublisherDAO(connectionProvider.getConnection());

        bookDAO.deleteById(1000);
        publisherDAO.deleteById(1000);
    }

    @Test
    void findByIdStrict_SavePublisher_SaveBook_ReturnEntityToDTO_OfBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Test publisher");
        publisher.setId(1000);
        publisherDAO.save(publisher);

        long id = 1000;
        Book expectedBook = new Book();
        expectedBook.setId(1000);
        expectedBook.setTitle("Test book");
        expectedBook.setPublisher(publisher);

        bookDAO.save(expectedBook);
        Book actualBook = bookDAO.findByIdStrict(1000);

        BookMapper bookMapper = new BookMapperImpl();

        assertEquals(bookMapper.toBookDTO(expectedBook).toString(),
                bookMapper.toBookDTO(actualBook).toString());
    }

    @Test
    void savePublisher_SaveBook_ReturnTrueOrFalse() {
        Publisher publisher = new Publisher();
        publisher.setName("Test publisher");
        publisher.setId(1000);
        publisherDAO.save(publisher);

        long id = 1000;
        Book expectedBook = new Book();
        expectedBook.setId(1000);
        expectedBook.setTitle("Test book");
        expectedBook.setPublisher(publisher);

        assertTrue(bookDAO.save(expectedBook));
    }

    @Test
    void updateById_SavePublisherAndBook__ChangeNameOfBook_ReturnChangedBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Test publisher");
        publisher.setId(1000);
        publisherDAO.save(publisher);

        long id = 1000;
        Book expectedBook = new Book();
        expectedBook.setId(1000);
        expectedBook.setTitle("Test book");
        expectedBook.setPublisher(publisher);

        bookDAO.save(expectedBook);
        bookDAO.updateById(1000,"New title");
        Book actualBook = bookDAO.findByIdStrict(1000);

        BookMapper bookMapper = new BookMapperImpl();

        expectedBook.setTitle("New title");

        assertEquals(bookMapper.toBookDTO(expectedBook).toString(),
                bookMapper.toBookDTO(actualBook).toString());
    }

    @Test
    void deleteByIdBook_AssertTrueIfDeleted() {
        Publisher publisher = new Publisher();
        publisher.setName("Test publisher");
        publisher.setId(1000);
        publisherDAO.save(publisher);

        long id = 1000;
        Book expectedBook = new Book();
        expectedBook.setId(1000);
        expectedBook.setTitle("Test book");
        expectedBook.setPublisher(publisher);

        assertTrue(bookDAO.deleteById(1000));
    }
}