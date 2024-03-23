package org.library.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDTO {
    private long id;
    private String title;
    private List<String> authors;
    private String publisher;

    public BookDTO(long id, String title, List<String> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
