package db.servlet;

import db.service.impl.CommentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/comments")
public class CommentServlet extends HttpServlet {

    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Комментарии</h1>");
            printWriter.write("<ul>");
            commentService.findAll().forEach(commentFilter ->
                    printWriter.write("""
                            <li>
                                %s - %s 
                            </li>
                            """.formatted(commentFilter.getContent(), commentFilter.getCreatedAt()))
            );
            printWriter.write("</ul>");
        }
    }
}

