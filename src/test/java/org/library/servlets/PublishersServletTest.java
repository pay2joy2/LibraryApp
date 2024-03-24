package org.library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.library.dto.PublisherDTO;
import org.library.service.PublisherService;
import org.mockito.Mockito;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class PublishersServletTest {

    @Test
    void doGet() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"publisherId\":1}")));

        PublisherService publisherService = Mockito.mock(PublisherService.class);
        PublisherDTO publisherDTO = new PublisherDTO(1, "Test Publisher", Arrays.asList("Test","OtherBook"));

        long id = 1;
        when(publisherService.getById(id)).thenReturn(publisherDTO);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PublishersServlet publishersServlet = new PublishersServlet();
        publishersServlet.setPublisherService(publisherService);

        publishersServlet.doGet(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals(publisherDTO.toString(), result);
    }

    @Test
    void doPost() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"publisherId\":1, \"publisherName\":\"Test Publisher\"}")));
        PublisherService publisherService = Mockito.mock(PublisherService.class);

        when(publisherService.save(any(PublisherDTO.class))).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PublishersServlet publishersServlet = new PublishersServlet();
        publishersServlet.setPublisherService(publisherService);

        publishersServlet.doPost(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals("Publisher inserted", result);
    }

    @Test
    void doPut() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"publisherId\":1, \"publisherName\":\"Updated Publisher\"}")));

        PublisherService publisherService = Mockito.mock(PublisherService.class);
        when(publisherService.updateById(anyLong(), anyString())).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PublishersServlet publishersServlet = new PublishersServlet();
        publishersServlet.setPublisherService(publisherService);

        publishersServlet.doPut(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals("Update completed", result);
    }

    @Test
    void doDelete() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"publisherId\":1}")));

        PublisherService publisherService = Mockito.mock(PublisherService.class);
        when(publisherService.deleteById(anyLong())).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PublishersServlet publishersServlet = new PublishersServlet();
        publishersServlet.setPublisherService(publisherService);

        publishersServlet.doDelete(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals("Deletion completed", result);
    }
}