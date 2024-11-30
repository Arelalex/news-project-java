package db.servlet;

import db.service.impl.NewsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryIdParam = req.getParameter("categoryId");

        Integer categoryId = null;
        if (categoryIdParam != null && !categoryIdParam.isBlank()) {
            try {
                categoryId = Integer.valueOf(categoryIdParam);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Новости</h1>");
            printWriter.write("<ul>");

            if (categoryId != null) {
                newsService.findByCategoryId(categoryId).forEach(newsFilter ->
                        printWriter.write("""
                                <br>
                                <li>
                                    <strong>%s</strong><br>
                                    <span>%s</span>
                                </li>
                                """.formatted(newsFilter.getTitle(), newsFilter.getDescription()))
                );
            } else {
                newsService.findAll().forEach(newsFilter ->
                        printWriter.write("""
                                <br>
                                <li>
                                    <strong>%s</strong><br>
                                    <span>%s</span>
                                </li>
                                """.formatted(newsFilter.getTitle(), newsFilter.getDescription()))
                );
            }

            printWriter.write("</ul>");
        }
    }
}
