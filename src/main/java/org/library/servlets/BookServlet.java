package org.library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.library.dto.BookDTO;
import org.library.service.BookService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    /**
     * Класс сервиса bookService
     */
    private BookService bookService = new BookService();

    /**
     * Конструктор класса для Mockito
     * @param bookService
     */
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * Получение GET запроса.
     * По bookId выполняется поиск книги.
     * Происходит вывод информации о ней.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try{
            long id = json.getLong("bookId");
            BookDTO bookDTO = bookService.getById(id);
            PrintWriter printWriter = resp.getWriter();
            if(bookDTO == null){
                printWriter.println("Book not found");
            } else {
                printWriter.println(bookDTO.toString());
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Получение POST запроса.
     * Добавление книги, связанное с остальными сущностями.
     * Авторы передаются в виде массива Long,
     * после идёт парсинг в стринг, для хранения в DTO.
     * Издатель так же передаётся как Long.
     * Связь осуществляется по id издателей и авторов.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try{
            long id = json.getLong("bookId");
            String title = json.getString("bookTitle");
            String publisher = String.valueOf(json.getLong("publisherId"));
            JSONArray array = json.getJSONArray("authorsId");

            List<String> authors = new ArrayList<>();
            for(int i = 0; i < array.length(); i++){
                authors.add(String.valueOf(array.getLong(i)));
            }

            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(id);
            bookDTO.setTitle(title);
            bookDTO.setPublisher(publisher);
            bookDTO.setAuthors(authors);

            PrintWriter printWriter = resp.getWriter();
            if(bookService.save(bookDTO)){
                printWriter.println("Book saved");
            } else {
                printWriter.println("Something went wrong");
            }


        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Получение POST запроса.
     * Обновление названия книги.
     * bookId - Id книги
     * bookTitle - Новое название книги
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
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

    /**
     * Получение DELETE запроса
     * Удаление книги из БД по bookId
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
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
