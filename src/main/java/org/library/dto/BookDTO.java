package org.library.dto;

import java.util.List;
import java.util.Objects;

public class BookDTO {
    private long id;
    private String title;
    private List<String> authors;
    private String publisher;

    public BookDTO(long id, String title, List<String> authors, String publisher) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
    }

    public BookDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id == bookDTO.id && Objects.equals(title, bookDTO.title) && Objects.equals(authors, bookDTO.authors) && Objects.equals(publisher, bookDTO.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authors, publisher);
    }
}
