package org.library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.library.dto.AuthorDTO;
import org.library.service.AuthorService;
import org.mockito.Mockito;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class AuthorServletTest {

    @Test
    void doGet() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"authorId\":1}")));
        AuthorService authorService = Mockito.mock(AuthorService.class);
        AuthorDTO authorDTO = new AuthorDTO(1, "Test Author", Arrays.asList("Test","OtherBook"));

        when(authorService.getById((anyLong()))).thenReturn(authorDTO);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        AuthorServlet authorServlet = new AuthorServlet();
        authorServlet.setAuthorService(authorService);
        authorServlet.doGet(request,response);
        writer.flush();

        String result = stringWriter.toString().trim();
        assertEquals(authorDTO.toString(), result);
    }

    @Test
    void doPost() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        long id = 1;
        String name = "Test Author";
        String inputJson = String.format("{\"authorId\":%d,\"authorName\":\"%s\"}", id, name);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(inputJson)));
        AuthorService authorService = Mockito.mock(AuthorService.class);
        when(authorService.save(any(AuthorDTO.class))).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        AuthorServlet authorServlet = new AuthorServlet();
        authorServlet.setAuthorService(authorService);
        authorServlet.doPost(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals("Author Inserted", result);
    }

    @Test
    void doPut() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        long id = 1;
        String name = "Updated Author";
        String inputJson = String.format("{\"authorId\":%d,\"authorName\":\"%s\"}", id, name);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(inputJson)));

        AuthorService authorService = Mockito.mock(AuthorService.class);
        when(authorService.updateById(id, name)).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        AuthorServlet authorServlet = new AuthorServlet();
        authorServlet.setAuthorService(authorService);
        authorServlet.doPut(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals("Update completed", result);
    }

    @Test
    void doDelete() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        long id = 1;
        String inputJson = String.format("{\"authorId\":%d}", id);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(inputJson)));

        AuthorService authorService = Mockito.mock(AuthorService.class);
        when(authorService.deleteById(id)).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        AuthorServlet authorServlet = new AuthorServlet();
        authorServlet.setAuthorService(authorService);
        authorServlet.doDelete(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals("Deletion completed", result);
    }
}