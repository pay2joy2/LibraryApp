package org.library.dao;

import org.library.entities.Author;
import org.library.entities.Book;
import org.library.entities.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TestAllQuery {
    private static Map<Long, Book> bookMap;
    private static Map<Long, Author> authorMap;
    private static Map<Long, Publisher> publishersMap;

    public Map<Long, Book> getBookMap() {
        return bookMap;
    }

    public Map<Long, Author> getAuthorMap() {
        return authorMap;
    }

    public Map<Long, Publisher> getPublishersMap() {
        return publishersMap;
    }

    public TestAllQuery(Connection connection){
        mapsInitialization(connection);
    }

    private static void mapsInitialization(Connection connection) {
        String query =
                "SELECT * " +
                        "FROM booksauthors " +
                        "INNER JOIN authors ON authors.authorid = booksauthors.authorid " +
                        "INNER JOIN books ON books.bookid = booksauthors.bookid " +
                        "INNER JOIN publishers ON publishers.publisherid = books.publisherid";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            bookMap = new HashMap<>();
            authorMap = new HashMap<>();
            publishersMap = new HashMap<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("BookId"));
                book.setTitle(resultSet.getString("BookTitle"));

                Author author = new Author();
                author.setId(resultSet.getLong("AuthorId"));
                author.setName(resultSet.getString("AuthorName"));

                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getLong("publisherid"));
                publisher.setName(resultSet.getString("publishername"));

                if (bookMap.containsKey(book.getId())) {
                    author.addBooks(book);
                    bookMap.get(book.getId()).addAuthors(author);
                    authorMap.put(author.getId(), author);
                } else if (authorMap.containsKey(author.getId())) {
                    book.addAuthors(author);
                    authorMap.get(author.getId()).addBooks(book);
                    bookMap.put(book.getId(), book);
                } else {
                    book.addAuthors(author);
                    author.addBooks(book);
                    bookMap.put(book.getId(), book);
                    authorMap.put(author.getId(), author);
                }
                if (publishersMap.containsKey(publisher.getId())) {
                    if (!publishersMap.get(publisher.getId()).getBooks().contains(bookMap.get(book.getId()))) {
                        bookMap.get(book.getId()).setPublisher(publishersMap.get(publisher.getId()));
                        publishersMap.get(publisher.getId()).addBooks(bookMap.get(book.getId()));
                    }
                } else {
                    publisher.addBooks(bookMap.get(book.getId()));
                    bookMap.get(book.getId()).setPublisher(publisher);
                    publishersMap.put(publisher.getId(), publisher);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
