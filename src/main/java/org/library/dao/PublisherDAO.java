package org.library.dao;

import org.library.entities.Author;
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
        TestAllQuery testAllQuery = new TestAllQuery(connection);
        return testAllQuery.getPublishersMap().get(id);
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
//        String query =
//                "INSERT INTO publishers " +
//                "VALUES (?,?)";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
//            preparedStatement.setLong(1, publisher.getId());
//            preparedStatement.setString(2,publisher.getName());
//            preparedStatement.executeUpdate();
//            return true;
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return false;

        return false;
    }


    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public boolean updateById(long id, String name) {
        return false;
    }
}
