package db.servlet;

import db.service.impl.PortalUserServiceImpl;
import db.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PortalUser", urlPatterns = "/users")
public class PortalUserServlet extends HttpServlet {

    private final PortalUserServiceImpl portalUserService = PortalUserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userIdParam = req.getParameter("userId");

        if (userIdParam != null && !userIdParam.isBlank()) {
            try {
                var userId = Integer.valueOf(userIdParam);

                req.setAttribute("user", portalUserService.findById(userId));
                req.getRequestDispatcher(JspHelper.getPath("users-details"))
                        .forward(req, resp);
                return;
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        req.setAttribute("users", portalUserService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("users"))
                .forward(req, resp);
    }
}


