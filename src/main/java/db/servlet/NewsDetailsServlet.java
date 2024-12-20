package db.servlet;

import db.dto.CommentDto;
import db.enums.JspPage;
import db.service.impl.CommentServiceImpl;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import db.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.NEWS_DETAILS)
public class NewsDetailsServlet extends HttpServlet {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();
    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var newsIdParam = req.getParameter("newsId");
        if (newsIdParam != null && !newsIdParam.isBlank()) {
            try {
                var newsId = Long.valueOf(newsIdParam);

                req.setAttribute("news", newsService.findById(newsId));
                req.setAttribute("comments", commentService.findAllByFilter(new CommentDto(newsId)));
                req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.NEWS_DETAILS_JSP))
                        .forward(req, resp);
                return;
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }

        req.setAttribute("news", newsService.findAll());
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.NEWS_JSP))
                .forward(req, resp);
    }
}


