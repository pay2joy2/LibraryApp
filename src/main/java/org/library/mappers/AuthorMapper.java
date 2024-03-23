package org.library.mappers;

import org.library.dto.AuthorDTO;
import org.library.entities.Author;
import org.library.entities.Book;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface AuthorMapper {

    AuthorDTO toAuthorDTO(Author author);
    Author toAuthor(AuthorDTO authorDTO);

    default ArrayList<String> map(List<Book> value){
        ArrayList<String> arrayList = new ArrayList<>();
        for (Book book : value){
            arrayList.add(book.getTitle());
        }
        return arrayList;
    }
    default ArrayList<Book> mapper(List<String> value){
        return new ArrayList<>();
    }

}
