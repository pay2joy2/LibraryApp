package org.library.service;

import org.library.dao.BookDAO;
import org.library.dto.BookDTO;
import org.library.entities.Book;
import org.library.mappers.BookMapper;
import org.library.mappers.BookMapperImpl;

public class BookService extends AbstractService<BookDTO> {

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
    public boolean save(BookDTO bookDTO) {
        return execute(connection -> {
            BookDAO bookDAO = new BookDAO(connection);
            BookMapper bookMapper = new BookMapperImpl();
            Book book = bookMapper.toBook(bookDTO);

            if(bookDAO.save(book)) {
                for (int i = 0; i < bookDTO.getAuthors().size(); i++) {
                    bookDAO.saveBooksAuthorsTable(book.getId(), Long.parseLong(bookDTO.getAuthors().get(i)));
                }
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean deleteById(long id) {
        return execute(connection -> {
            BookDAO bookDAO = new BookDAO(connection);
            return bookDAO.deleteById(id);
        });
    }

    @Override
    public boolean updateById(long id, String title) {
        return execute(connection -> {
            BookDAO bookDAO = new BookDAO(connection);
            return bookDAO.updateById(id,title);
        });
    }
}
