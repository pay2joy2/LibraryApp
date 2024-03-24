package org.library.service;

import org.library.dao.PublisherDAO;
import org.library.dto.PublisherDTO;
import org.library.entities.Publisher;
import org.library.mappers.PublisherMapper;
import org.library.mappers.PublisherMapperImpl;
public class PublisherService extends AbstractService<PublisherDTO> {
    private static PublisherService instance;

    public static PublisherService getInstance(){
        if (instance == null){
            instance = new PublisherService();
        }
        return instance;
    }
    @Override
    public PublisherDTO getById(long id) {
        return execute(connection -> {
            PublisherDAO publisherDAO = new PublisherDAO(connection);
            PublisherMapper publisherMapper = new PublisherMapperImpl();
            PublisherDTO publisherDTO = publisherMapper.toPublisherDTO(publisherDAO.findById(id));
            if(publisherDTO == null){
                publisherDTO = publisherMapper.toPublisherDTO(publisherDAO.findByIdStrict(id));
            }
            return publisherDTO;
        });
    }

    @Override
    public boolean save(PublisherDTO publisherDTO) {
        return execute(connection -> {
            PublisherDAO publisherDAO = new PublisherDAO(connection);
            PublisherMapper publisherMapper = new PublisherMapperImpl();
            Publisher publisher = publisherMapper.toPublisher(publisherDTO);
            return publisherDAO.save(publisher);
        });
    }

    @Override
    public boolean deleteById(long id) {
        return execute(connection -> {
            PublisherDAO publisherDAO = new PublisherDAO(connection);
            return publisherDAO.deleteById(id);
        });
    }

    @Override
    public boolean updateById(long id, String name) {
        return execute(connection -> {
            PublisherDAO publisherDAO = new PublisherDAO(connection);
            return publisherDAO.updateById(id,name);
        });
    }
}
