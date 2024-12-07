package db.servlet;

import db.service.NewsService;
import db.service.impl.NewsServiceImpl;
import db.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/categories/news")
public class NewsCategoryServlet extends HttpServlet {

    private final NewsService newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var categoryIdParam = req.getParameter("categoryId");

        if (categoryIdParam != null && !categoryIdParam.isBlank()) {
            try {
                var categoryId = Integer.valueOf(categoryIdParam);
                req.setAttribute("news", newsService.findByCategoryId(categoryId));

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

