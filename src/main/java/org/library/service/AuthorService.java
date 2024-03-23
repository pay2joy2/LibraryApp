package org.library.service;

import org.library.dao.AuthorDAO;
import org.library.db.ConnectionFactory;
import org.library.dto.AuthorDTO;
import org.library.mappers.AuthorMapper;
import org.library.mappers.AuthorMapperImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthorService extends AbstractService<AuthorDTO>{

    private static final ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private static AuthorService instance;

    public static AuthorService getInstance(){
        if (instance == null){
            instance = new AuthorService();
        }
    return instance;
    }

    @Override
    public AuthorDTO getById(long id){
        Connection connection = connectionFactory.getConnection();
        AuthorDAO authorDAO = new AuthorDAO(connection);
        AuthorMapper authorMapper = new AuthorMapperImpl();
        AuthorDTO authorDTO = authorMapper.toAuthorDTO(authorDAO.findById(id));
        if(authorDTO == null){
            authorDTO = authorMapper.toAuthorDTO(authorDAO.findByIdStrict(id));
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authorDTO;
    }

    @Override
    public boolean deleteById(long id){
        Connection connection = connectionFactory.getConnection();
        AuthorDAO authorDAO = new AuthorDAO(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authorDAO.deleteById(id);
    }

    @Override
    public boolean updateById(long id, String name){
        Connection connection = connectionFactory.getConnection();
        AuthorDAO authorDAO = new AuthorDAO(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authorDAO.updateById(id, name);
    }
}
