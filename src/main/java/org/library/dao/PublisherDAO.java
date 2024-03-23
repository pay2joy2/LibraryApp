package org.library.dao;

import org.library.entities.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherDAO extends AbstractDAO<Publisher> {
    public PublisherDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Publisher findById(long id) {
        ReceiveWholeQuery receiveWholeQuery = new ReceiveWholeQuery(connection);
        return receiveWholeQuery.getPublishersMap().get(id);
    }

    @Override
    public Publisher findByIdStrict(long id) {
        String query =
                "SELECT * FROM publishers " +
                        "WHERE publisherId = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Publisher publisher = new Publisher();
                publisher.setName(resultSet.getString("publisherName"));
                publisher.setId(resultSet.getLong("publisherId"));
                return publisher;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Publisher publisher) {
        return false;
    }


    @Override
    public boolean deleteById(long id) {
        String query =
                "DELETE FROM publishers " +
                "WHERE publisherId = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(long id, String name) {
        String query =
                "UPDATE publishers" +
                "SET publishersName = ? " +
                "WHERE publisherId = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
