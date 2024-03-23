package org.library.service;

import org.library.dao.PublisherDAO;
import org.library.db.ConnectionFactory;
import org.library.dto.PublisherDTO;
import org.library.mappers.PublisherMapper;
import org.library.mappers.PublisherMapperImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class PublisherService extends AbstractService<PublisherDTO> {

    private static final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static PublisherService instance;

    public static PublisherService getInstance(){
        if (instance == null){
            instance = new PublisherService();
        }
        return instance;
    }
    @Override
    public PublisherDTO getById(long id) {
        Connection connection = connectionFactory.getConnection();
        PublisherDAO publisherDAO = new PublisherDAO(connection);
        PublisherMapper publisherMapper = new PublisherMapperImpl();
        PublisherDTO publisherDTO = publisherMapper.toPublisherDTO(publisherDAO.findById(id));
        if(publisherDTO == null){
            publisherDTO = publisherMapper.toPublisherDTO(publisherDAO.findByIdStrict(id));
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return publisherDTO;
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
