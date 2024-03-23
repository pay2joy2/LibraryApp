package org.library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.library.dao.BookDAO;
import org.library.dao.PublisherDAO;
import org.library.db.ConnectionFactory;
import org.library.dto.AuthorDTO;
import org.library.dto.PublisherDTO;
import org.library.entities.Publisher;
import org.library.mappers.BookMapper;
import org.library.mappers.BookMapperImpl;
import org.library.mappers.PublisherMapper;
import org.library.mappers.PublisherMapperImpl;
import org.library.service.PublisherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
//        Connection connection = connectionFactory.getConnection();
//        PublisherDAO publisherDAO = new PublisherDAO(connection);
//
//        BufferedReader bufferedReader = req.getReader();
//        String body = bufferedReader.lines().collect(Collectors.joining());
//        JSONObject json = new JSONObject(body);
//        try {
//            Publisher publisher = new Publisher();
//            publisher.setId(json.getLong("publisherId"));
//            publisher.setName(json.getString("publisherName"));
//            publisherDAO.save(publisher);
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
