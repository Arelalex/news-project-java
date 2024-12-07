package db.servlet;

import db.service.impl.CommentServiceImpl;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/comments")
public class CommentServlet extends HttpServlet {

    private final CommentServiceImpl commentService = CommentServiceImpl.getInstance();
    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var newsIdParam = req.getParameter("newsId");

        if (newsIdParam != null && !newsIdParam.isBlank()) {
            try {
                var newsId = Long.valueOf(newsIdParam);
                req.setAttribute("comments", commentService.findAll());
                req.setAttribute("news", newsService.findById(newsId));

            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        } else {
            req.setAttribute("comments", commentService.findAll());
        }
        req.getRequestDispatcher(JspHelper.getPath("comments"))
                .forward(req, resp);
    }
}

