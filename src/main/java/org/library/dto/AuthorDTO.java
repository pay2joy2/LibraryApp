package org.library.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuthorDTO {
    private long id;
    private String name;

    /**
     * Для простоты вывода пользователю, и отсутствию связанности,
     * List класса Book упрощён до вывода только их названия.
     */
    private List<String> books;

    public AuthorDTO(long id, String name, List<String> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public AuthorDTO() {
        this.books = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return id == authorDTO.id && Objects.equals(name, authorDTO.name) && Objects.equals(books, authorDTO.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, books);
    }
}
