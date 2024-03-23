package org.library.entities;

import java.util.*;

public class Publisher {

    private long id;
    private String name;
    private List<Book> books;

    public Publisher() {
        this.books = new ArrayList<>();
    }

    public Publisher(long id, String name, List<Book> books) {
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

    public void addBooks(Book book) {books.add(book);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id == publisher.id && Objects.equals(name, publisher.name) && Objects.equals(books, publisher.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, books);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" +  booksToString() +
                    '}';

    }

    private String booksToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Book book : books){
            stringBuilder.append(book.getTitle()).append(" ");
        }
        return stringBuilder.toString();
    }

}
