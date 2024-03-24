package org.library.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherDTO that = (PublisherDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, books);
    }
}
