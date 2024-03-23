package org.library.mappers;

import org.library.dto.BookDTO;
import org.library.entities.Author;
import org.library.entities.Book;
import org.library.entities.Publisher;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper
public interface BookMapper {
    BookDTO toBookDTO(Book book);
    Book toBook(BookDTO bookDTO);

    default ArrayList<String> map(List<Author> value){
        ArrayList<String> arrayList = new ArrayList<>();
        for (Author author : value){
            arrayList.add(author.getName());
        }
        return arrayList;
    }

    default List<Author> mapper(List<String> value){
        return Collections.emptyList();
    }
    default String map(Publisher value){return value.getName();}
    default Publisher map(String value){return null;}
}