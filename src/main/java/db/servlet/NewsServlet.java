package db.servlet;

import db.dto.NewsDto;
import db.enums.JspPage;
import db.enums.Statuses;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.NEWS)
public class NewsServlet extends HttpServlet {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("news", newsService.findAllByFilter(new NewsDto(null, null, Statuses.APPROVED.getId())));
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.NEWS_JSP))
                .forward(req, resp);
    }
}

