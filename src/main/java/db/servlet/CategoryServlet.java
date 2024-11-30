package db.servlet;

import db.service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    private final CategoryServiceImpl categoryService = CategoryServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список категорий:</h1>");
            printWriter.write("<ul>");
            categoryService.findAll().forEach(categoryFilter -> {
                printWriter.write("""
                        <li>
                            <a href="/news?categoryId=%d">%s</a>
                        </li>
                        """.formatted(categoryFilter.getId(), categoryFilter.getCategory()));
            });
            printWriter.write("</ul>");
        }
    }
}




