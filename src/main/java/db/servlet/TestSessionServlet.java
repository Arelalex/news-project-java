package db.servlet;

import db.dto.PortalUserFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sessions")
public class TestSessionServlet extends HttpServlet {

    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var user = (PortalUserFilter) session.getAttribute(USER);
        if (user == null) {
            user = PortalUserFilter.builder().
                    id(1)
                    // .email("test@gmail.com")
                    .build();
            session.setAttribute(USER, user);
        }

    }
}
