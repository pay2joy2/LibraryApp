package org.library.service;

import org.library.dao.BookDAO;
import org.library.dto.BookDTO;
import org.library.mappers.BookMapper;
import org.library.mappers.BookMapperImpl;

public class BookService extends AbstractService<BookDTO> {

    private static BookService instance;
    public static BookService getInstance(){
        if (instance == null){
            instance = new BookService();
        }
        return instance;
    }

    @Override
    public BookDTO getById(long id) {
        return execute(connection -> {
            BookDAO bookDAO = new BookDAO(connection);
            BookMapper bookMapper = new BookMapperImpl();
            BookDTO bookDTO = bookMapper.toBookDTO(bookDAO.findById(id));
            if (bookDTO == null) {
                bookDTO = bookMapper.toBookDTO(bookDAO.findByIdStrict(id));
            }
            return bookDTO;
        });
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
