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

    /**
     * Для упрощения вывода,
     * осуществляется только вывод имён авторов,
     * без хранения сущностей.
     * @param value Список сущностей авторов
     * @return Список имён авторов
     */
    default ArrayList<String> map(List<Author> value){
        ArrayList<String> arrayList = new ArrayList<>();
        for (Author author : value){
            arrayList.add(author.getName());
        }
        return arrayList;
    }

    /**
     * Обратная связь без полного перебора связанностей невозможна.
     * Возвращается пустой список авторов.
     * @param value Список имён авторов.
     * @return Пустой список
     */
    default List<Author> mapper(List<String> value){
        return Collections.emptyList();
    }

    /**
     * Для упрощения возвращается только название издателя.
     * @param value Entity издателя
     * @return Имя издателя
     */
    default String map(Publisher value){return value.getName();}

    /**
     * Парсинг строки, в которой есть Long число. Создаётся сущность Publisher
     * Метод только для Book(BookDTO), для правильного возвращения из BookServlet
     * @param value Строка содержащая Long
     * @return Возвращается сущность издателя не имеющая инициализации имени.
     */
    default Publisher map(String value){
        Publisher publisher = new Publisher();
        publisher.setId(Long.parseLong(value));
        return publisher;
        }
}