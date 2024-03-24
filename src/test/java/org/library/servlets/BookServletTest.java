package org.library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.library.dto.BookDTO;
import org.library.service.BookService;
import org.mockito.Mockito;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class BookServletTest {

    @Test
    void doGet() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"bookId\":1}")));

        BookService bookService = Mockito.mock(BookService.class);
        BookDTO bookDTO = new BookDTO(1, "Test Book", Arrays.asList("Test","Max"), "JamaicaPrint");

        long id = 1;
        when(bookService.getById(id)).thenReturn(bookDTO);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        BookServlet bookServlet = new BookServlet();
        bookServlet.setBookService(bookService);
        bookServlet.doGet(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals(bookDTO.toString(), result);
    }

    @Test
    void doPost() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"bookId\":1, \"bookTitle\":\"Test Book\", \"publisherId\":1, \"authorsId\":[1, 2]}")));

        BookService bookService = Mockito.mock(BookService.class);
        when(bookService.save(any(BookDTO.class))).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        BookServlet bookServlet = new BookServlet();
        bookServlet.setBookService(bookService);
        bookServlet.doPost(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals("Book saved", result);
    }

    @Test
    void doPut() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"bookId\":1, \"bookTitle\":\"Updated Book\"}")));

        BookService bookService = Mockito.mock(BookService.class);
        when(bookService.updateById(anyLong(), anyString())).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        BookServlet bookServlet = new BookServlet();
        bookServlet.setBookService(bookService);
        bookServlet.doPut(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals("Update completed", result);
    }

    @Test
    void doDelete() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{\"bookId\":1}")));

        BookService bookService = Mockito.mock(BookService.class);
        when(bookService.deleteById(anyLong())).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        BookServlet bookServlet = new BookServlet();
        bookServlet.setBookService(bookService);
        bookServlet.doDelete(request, response);

        writer.flush();
        String result = stringWriter.toString().trim();
        assertEquals("Deletion completed", result);
    }

}