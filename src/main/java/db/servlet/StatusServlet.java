package db.servlet;

import db.dao.impl.StatusDaoImpl;
import db.enums.JspPage;
import db.service.impl.StatusServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.STATUSES)
public class StatusServlet extends HttpServlet {

    private final StatusDaoImpl statusDao = StatusDaoImpl.getInstance();
    private final StatusServiceImpl statusService = StatusServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("statuses", statusService.findAll());
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.STATUSES_JSP))
                .forward(req, resp);
    }
}

