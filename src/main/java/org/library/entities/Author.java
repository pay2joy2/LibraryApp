package org.library.entities;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private long id;
    private String name;
    private List<Book> books;

    public Author() {
        this.books = new ArrayList<>();
    }

    public Author(long id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void addBooks(Book book){books.add(book);}
}
