package org.library.dao;

import org.library.entities.Book;
import org.library.entities.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO extends AbstractDAO<Book> {
    public BookDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Book findById(long id) {
        ReceiveWholeQuery receiveWholeQuery = new ReceiveWholeQuery(connection);
        return receiveWholeQuery.getBookMap().get(id);
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
    public boolean save(Book book) {
        String query =
                "INSERT INTO books " +
                "VALUES (?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, book.getId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setLong(3, book.getPublisher().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void saveBooksAuthorsTable(long bookId, long authorId){
        String query =
                "INSERT INTO BooksAuthors " +
                "VALUES (?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,bookId);
            preparedStatement.setLong(2,authorId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateById(long id, String newTitle){
        String query =
                "UPDATE books " +
                "SET bookTitle = ? " +
                "WHERE bookId = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,newTitle);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
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
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
