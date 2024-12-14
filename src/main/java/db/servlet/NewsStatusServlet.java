package db.servlet;

import db.enums.JspPage;
import db.enums.Statuses;
import db.service.NewsService;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.STATUSES + UrlPath.NEWS)
public class NewsStatusServlet extends HttpServlet {

    private final NewsService newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var statusName = req.getParameter("status");
        if (statusName != null && !statusName.isBlank()) {
            try {
                Statuses status = Statuses.valueOf(statusName);
                req.setAttribute("news", newsService.findByStatusId(status.getId()));

            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        } else {
            req.setAttribute("news", newsService.findAll());
        }
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_JSP))
                .forward(req, resp);
    }
}

