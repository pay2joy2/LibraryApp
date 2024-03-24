package org.library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.library.dto.PublisherDTO;
import org.library.service.PublisherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/publisher")
public class PublishersServlet extends HttpServlet {

    /**
     * Класс сервиса PublisherService
     */
    private PublisherService publisherService = new PublisherService();

    /**
     * Конструктор класса для Mockito
     * @param publisherService
     */
    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    /**
     * Получение GET запроса.
     * По publisherID выполняется поиск издателя.
     * Происходит вывод информации о нём.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try {
            long id = json.getLong("publisherId");
            PublisherDTO publisherDTO = publisherService.getById(id);
            resp.getWriter().println(publisherDTO.toString());
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Получение POST запроса.
     * Добавление издателя.
     * Добавление не связано с другими сущностями.
     * publisherId - уникальный id издателя
     * publisherName - имя издателя
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try{
            long id = json.getLong("publisherId");
            String name = json.getString("publisherName");
            PublisherDTO publisherDTO = new PublisherDTO();
            publisherDTO.setId(id);
            publisherDTO.setName(name);
            PrintWriter printWriter = resp.getWriter();
            if(publisherService.save(publisherDTO)){
                printWriter.println("Publisher inserted");
            } else {
                printWriter.println("Something went wrong");
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Получение PUT запроса
     * Изменение имени издателя
     * publisherId - уникальный id издателя
     * publisherName - новое имя издателя
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try{
            long id = json.getLong("publisherId");
            String name = json.getString("publisherName");
            if(publisherService.updateById(id,name)){
                resp.getWriter().println("Update completed");
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Удаление издателя по его publisherId
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonStr = req.getReader().lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(jsonStr);
        try{
            long id = json.getLong("publisherId");
            if (publisherService.deleteById(id)){
                resp.getWriter().println("Deletion completed");
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
