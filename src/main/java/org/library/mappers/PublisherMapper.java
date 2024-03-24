package org.library.mappers;

import org.library.dto.PublisherDTO;
import org.library.entities.Book;
import org.library.entities.Publisher;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper
public interface PublisherMapper {

    PublisherDTO toPublisherDTO(Publisher publisher);
    Publisher toPublisher(PublisherDTO publisherDTO);


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
     * Возвращается пустой список книг
     * @param value Список названий книг.
     * @return Пустой список
     */
    default List<Book> mapper(List<String> value){
        return Collections.emptyList();
    }
}
