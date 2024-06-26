package org.library.dao;

import org.library.entities.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDAO extends AbstractDAO<Author>{

    /**
     * Конструктор класса
     * @param connection Экземпляр соединения с базой данных
     */
    public AuthorDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Author findById(long id) {
        ReceiveWholeQuery receiveWholeQuery = new ReceiveWholeQuery(connection);
        return receiveWholeQuery.getAuthorMap().get(id);
    }

    @Override
    public boolean save(Author author) {
        String query =
                "INSERT INTO authors " +
                "VALUES (?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,author.getId());
            preparedStatement.setString(2,author.getName());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Author findByIdStrict(long id){
        String query =
                "SELECT * FROM authors " +
                "WHERE authorId = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
              Author author = new Author();
              author.setName(resultSet.getString("authorName"));
              author.setId(resultSet.getLong("authorId"));
              return author;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(long id){
        String query =
                "DELETE FROM authors " +
                "WHERE authorId = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(long id, String name){
        String query =
                "UPDATE authors " +
                "SET authorName = ? " +
                "WHERE authorID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
