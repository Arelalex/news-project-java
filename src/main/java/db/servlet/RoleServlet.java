package db.servlet;

import db.service.impl.RoleServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/roles")
public class RoleServlet extends HttpServlet {

    private final RoleServiceImpl roleService = RoleServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Роли в системе</h1>");
            printWriter.write("<ul>");
            roleService.findAll().forEach(roleFilter -> printWriter.write("""
                    <li>
                        %s
                    </li>
                    """.formatted(roleFilter.getRole())));
            printWriter.write("</ul>");
        }
    }
}

