package org.library.service;

import org.library.dao.AuthorDAO;
import org.library.dto.AuthorDTO;
import org.library.entities.Author;
import org.library.mappers.AuthorMapper;
import org.library.mappers.AuthorMapperImpl;

public class AuthorService extends AbstractService<AuthorDTO>{

    @Override
    public AuthorDTO getById(long id){
        return execute(connection -> {
            AuthorDAO authorDAO = new AuthorDAO(connection);
            AuthorMapper authorMapper = new AuthorMapperImpl();
            AuthorDTO authorDTO = authorMapper.toAuthorDTO(authorDAO.findById(id));
            if(authorDTO == null){
                authorDTO = authorMapper.toAuthorDTO(authorDAO.findByIdStrict(id));
            }
            return authorDTO;
        });
    }

    @Override
    public boolean save(AuthorDTO authorDTO) {
        return execute(connection -> {
            AuthorDAO authorDAO = new AuthorDAO(connection);
            AuthorMapper authorMapper = new AuthorMapperImpl();
            Author author = authorMapper.toAuthor(authorDTO);
            return authorDAO.save(author);
        });
    }

    @Override
    public boolean deleteById(long id){
        return execute(connection -> {
            AuthorDAO authorDAO = new AuthorDAO(connection);
            return authorDAO.deleteById(id);
        });
    }

    @Override
    public boolean updateById(long id, String name){
        return execute(connection -> {
            AuthorDAO authorDAO = new AuthorDAO(connection);
            return authorDAO.updateById(id, name);
        });
    }
}
