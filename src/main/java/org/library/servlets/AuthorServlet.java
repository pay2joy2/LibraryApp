package org.library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.library.dto.AuthorDTO;
import org.library.service.AuthorService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/author")
public class AuthorServlet extends HttpServlet {

    /**
     * Класс сервиса authorService
     */
    private AuthorService authorService = new AuthorService();

    /**
     * Конструктор для тестов через Mockito.
     * @param authorService
     */
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Получение GET запроса.
     * По authorId выполняется поиск автора.
     * Происходит вывод информации о нём.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try{
            long id = json.getLong("authorId");
            AuthorDTO authorDTO = authorService.getById(id);
            resp.getWriter().println(authorDTO.toString());
        } catch (JSONException e){
            e.printStackTrace();
        }
    }


    /**
     * Получение POST запроса.
     * Добавление автора
     * добавление не связанно с другими сущностями.
     * authorId - уникальный id автора
     * authorName - Имя автора
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try{
            long id = json.getLong("authorId");
            String name = json.getString("authorName");
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(id);
            authorDTO.setName(name);
            PrintWriter printWriter = resp.getWriter();
            if(authorService.save(authorDTO)){
                printWriter.println("Author Inserted");
            } else {
                printWriter.println("Something went wrong");
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Получение PUT запроса.
     * Изменение имени автора.
     * authorId - выполняется поиск автора по id.
     * authorName - Новое имя автора.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try{
            long id = json.getLong("authorId");
            String name = json.getString("authorName");
            if(authorService.updateById(id,name)){
                resp.getWriter().println("Update completed");
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Удаление автора по его authorID
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try{
            long id = json.getLong("authorId");
            if (authorService.deleteById(id)){
                resp.getWriter().println("Deletion completed");
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

}
