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

    /**
     * Для упрощения вывода,
     * осуществляется только вывод названий книг,
     * без хранения сущностей.
     * @param value Список сущностей книг
     * @return Список названий книг
     */
    default ArrayList<String> map(List<Book> value){
        ArrayList<String> arrayList = new ArrayList<>();
        for (Book book : value){
            arrayList.add(book.getTitle());
        }
        return arrayList;
    }

    /**
     * Обратная связь без полного перебора связанностей невозможна.
     * Возвращается пустой список книг.
     * @param value Список названия книг.
     * @return Пустой список
     */
    default ArrayList<Book> mapper(List<String> value){
        return new ArrayList<>();
    }

}
