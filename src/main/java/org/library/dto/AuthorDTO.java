package org.library.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private long id;
    private String name;
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
}
