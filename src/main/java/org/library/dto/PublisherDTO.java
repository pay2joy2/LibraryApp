package org.library.dto;

import org.library.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class PublisherDTO {

    private long id;
    private String name;
    private List<String> books;

    public PublisherDTO(long id, String name, List<String> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public PublisherDTO() {
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
        return "PublisherDTO{" +
                "id=" + id +
                ", name=" + name +
                ", books=" + books +
                '}';
    }
}
