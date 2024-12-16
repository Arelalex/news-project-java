package db.servlet;

import db.enums.JspPage;
import db.service.impl.RoleServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.ROLES)
public class RoleServlet extends HttpServlet {

    private final RoleServiceImpl roleService = RoleServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.findAll());
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.ROLES_JSP))
                .forward(req, resp);
    }
}

