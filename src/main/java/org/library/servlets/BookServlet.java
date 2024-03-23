package org.library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.library.dto.BookDTO;
import org.library.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
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
            BookDTO bookDTO = bookService.getById(id);
            resp.getWriter().println(bookDTO.toString());
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        String body = bufferedReader.lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(body);
        try{
            long id = json.getLong("bookId");
            String name = json.getString("bookTitle");
            if(bookService.updateById(id,name)){
                resp.getWriter().println("Update completed");
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        String body = bufferedReader.lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(body);
        try{
            long id = json.getLong("bookId");
            if (bookService.deleteById(id)){
                resp.getWriter().println("Deletion completed");
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
