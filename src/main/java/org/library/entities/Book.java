package org.library.entities;

import org.library.dto.AuthorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    private long id;
    private String title;
    private Publisher publisher;

    private List<Author> authors;

    public Book(long id, String title, Publisher publisher, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.authors = authors;
    }

    public Book() {
        this.authors = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthors(Author author){
        authors.add(author);
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }


}
