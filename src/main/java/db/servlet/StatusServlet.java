package db.servlet;

import db.dao.impl.StatusDaoImpl;
import db.service.impl.StatusServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/statuses")
public class StatusServlet extends HttpServlet {

    private final StatusDaoImpl statusDao = StatusDaoImpl.getInstance();
    private final StatusServiceImpl statusService = StatusServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Статусы новостей и комментариев</h1>");
            printWriter.write("<ul>");
            statusService.findAll().forEach(statusFilter -> printWriter.write("""
                    <li>
                        %s
                    </li>
                    """.formatted(statusFilter.getStatus())));
            printWriter.write("</ul>");
        }
    }
}

