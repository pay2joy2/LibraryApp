package org.library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.library.dao.BookDAO;
import org.library.db.ConnectionFactory;
import org.library.dto.AuthorDTO;
import org.library.dto.BookDTO;
import org.library.entities.Author;
import org.library.entities.Book;
import org.library.entities.Publisher;
import org.library.service.AuthorService;
import org.library.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    private static BookService bookService = BookService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        String body = bufferedReader.lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(body);
        try{
            long id = json.getLong("bookId");
            BookDTO bookDTO= bookService.getById(id);
            resp.getWriter().println(bookDTO.toString());
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        Connection connection = connectionFactory.getConnection();
//        BookDAO bookDAO = new BookDAO(connection);
//        if(bookDAO.updateBookById(3L, "WAR")){
//            System.out.println("DONE");
//        } else {
//            System.out.println("ERROR");
//        }
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Connection connection = connectionFactory.getConnection();
//        BookDAO bookDAO = new BookDAO(connection);
//
//        bookDAO.deleteBookById(4);
//
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Connection connection = connectionFactory.getConnection();
//        BookDAO bookDAO = new BookDAO(connection);
//
//        BufferedReader bufferedReader = req.getReader();
//
//        String body = bufferedReader.lines().collect(Collectors.joining());
//
//        JSONObject json = new JSONObject(body);
//
//        Book book = new Book();
//
//        book.setTitle(json.getString("bookTitle"));
//        book.setId(json.getLong("bookId"));
//        book.setPublisher();
//
//        bookDAO.saveBookWithoutAuthors()
    }
}
