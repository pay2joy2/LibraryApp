package org.library.dao;

import org.library.db.ConnectionFactory;
import org.library.entities.Author;
import org.library.entities.Book;
import org.library.entities.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookDAO extends AbstractDAO<Book> {
    public BookDAO(Connection connection) {
        super(connection);
    }

//    @Override
//    public Book findById(long id){
//        String query =
//                        "SELECT * " +
//                        "FROM booksauthors " +
//                        "INNER JOIN authors ON authors.authorid = booksauthors.authorid " +
//                        "INNER JOIN books ON books.bookid = booksauthors.bookid " +
//                        "INNER JOIN publishers ON publishers.publisherid = books.publisherid";
//        try(PreparedStatement statement = connection.prepareStatement(query)){
//            ResultSet resultSet = statement.executeQuery();
//            Map<String, Book> bookMap = new HashMap<>();
//            Map<String, Author> authorMap = new HashMap<>();
//            Map<String, Publisher> publishersMap = new HashMap<>();
//            while(resultSet.next()){
//                Book book = new Book();
//                book.setId(resultSet.getLong("BookId"));
//                book.setTitle(resultSet.getString("BookTitle"));
//
//                Author author = new Author();
//                author.setId(resultSet.getLong("AuthorId"));
//                author.setName(resultSet.getString("AuthorName"));
//
//                Publisher publisher = new Publisher();
//                publisher.setId(resultSet.getLong("publisherid"));
//                publisher.setName(resultSet.getString("publishername"));
//
//                if(bookMap.containsKey(book.getTitle())){
//                    author.addBooks(book);
//                    bookMap.get(book.getTitle()).addAuthors(author);
//                    authorMap.put(author.getName(),author);
//                } else if (authorMap.containsKey(author.getName())) {
//                    book.addAuthors(author);
//                    authorMap.get(author.getName()).addBooks(book);
//                    bookMap.put(book.getTitle(), book);
//                } else {
//                    book.addAuthors(author);
//                    author.addBooks(book);
//                    bookMap.put(book.getTitle(), book);
//                    authorMap.put(author.getName(),author);
//                }
//                if(publishersMap.containsKey(publisher.getName())){
//                    if(!publishersMap.get(publisher.getName()).getBooks().contains(bookMap.get(book.getTitle()))){
//                        bookMap.get(book.getTitle()).setPublisher(publishersMap.get(publisher.getName()));
//                        publishersMap.get(publisher.getName()).addBooks(bookMap.get(book.getTitle()));
//                    }
//                } else {
//                    publisher.addBooks(bookMap.get(book.getTitle()));
//                    bookMap.get(book.getTitle()).setPublisher(publisher);
//                    publishersMap.put(publisher.getName(),publisher);
//                }
//            }
//            for(Book book: bookMap.values()){
//                if(book.getId() == id){
//                    return book;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public Book findById(long id) {
        TestAllQuery testAllQuery = new TestAllQuery(connection);
        return testAllQuery.getBookMap().get(id);
    }

    @Override
    public Book findByIdStrict(long id) {
            String query =
            "SELECT " +
                    "books.bookId, " +
                    "books.bookTitle, " +
                    "publishers.publisherId, " +
                    "publishers.publisherName " +
                    "FROM " +
                    "public.books " +
                    "LEFT JOIN " +
                    "public.publishers ON books.publisherId = publishers.publisherId " +
                    "WHERE bookId = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getLong("bookId"));
                book.setTitle(resultSet.getString("bookTitle"));

                Publisher publisher = new Publisher();
                publisher.setName(resultSet.getString("publisherName"));
                publisher.setId(resultSet.getLong("publisherId"));
                book.setPublisher(publisher);
                return book;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Book o) {
        return false;
    }

    @Override
    public boolean updateById(long id, String newTitle){
        String query =
                "UPDATE books " +
                "SET booktitle = ? " +
                "WHERE bookid = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,newTitle);
            preparedStatement.setLong(2,id);
            preparedStatement.executeQuery();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(long id){
        String query =
                "DELETE FROM books " +
                "WHERE bookId = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveBookWithoutAuthors(Book book){
        String query =
                "INSERT INTO books " +
                "VALUES (?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, book.getId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setLong(3,book.getPublisher().getId());
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
