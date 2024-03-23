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
import java.util.stream.Collectors;

@WebServlet("/publisher")
public class PublishersServlet extends HttpServlet {

    PublisherService publisherService = PublisherService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        String body = bufferedReader.lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(body);
        try {
            long id = json.getLong("publisherId");
            PublisherDTO publisherDTO = publisherService.getById(id);
            resp.getWriter().println(publisherDTO.toString());
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
            long id = json.getLong("publisherId");
            String name = json.getString("publisherName");
            if(publisherService.updateById(id,name)){
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
            long id = json.getLong("publisherId");
            if (publisherService.deleteById(id)){
                resp.getWriter().println("Deletion completed");
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
