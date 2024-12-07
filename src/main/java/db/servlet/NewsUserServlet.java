package db.servlet;

import db.dto.NewsDto;
import db.service.NewsService;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/news")
public class NewsUserServlet extends HttpServlet {

    private final NewsService newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userIdParam = req.getParameter("userId");

        if (userIdParam != null && !userIdParam.isBlank()) {
            try {
                var userId = Integer.valueOf(userIdParam);

                var filter = new NewsDto(userId);

                req.setAttribute("news", newsService.findAllByFilter(filter));

            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        } else {
            req.setAttribute("news", newsService.findAll());
        }
        req.getRequestDispatcher(JspHelper.getPath("news"))
                .forward(req, resp);
    }
}

