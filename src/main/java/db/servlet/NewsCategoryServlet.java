package db.servlet;

import db.dto.PortalUserDto;
import db.enums.JspPage;
import db.enums.Roles;
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

@WebServlet(UrlPath.CATEGORIES + UrlPath.NEWS)
public class NewsCategoryServlet extends HttpServlet {

    private final NewsService newsService = NewsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var categoryIdParam = req.getParameter("categoryId");
        if (categoryIdParam != null && !categoryIdParam.isBlank()) {
            try {
                var categoryId = Integer.valueOf(categoryIdParam);
                req.setAttribute("news", newsService.findByCategoryId(categoryId));

                var user = (PortalUserDto) req.getSession().getAttribute("user");
                if (user != null && user.getRole() == Roles.MODERATOR) {
                    req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.MODERATOR_JSP)).forward(req, resp);
                } else {
                    req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.NEWS_JSP)).forward(req, resp);
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        req.setAttribute("news", newsService.findAll());
        req.getRequestDispatcher(JspHelper.getPathJsp(JspPage.NEWS_JSP))
                .forward(req, resp);
    }
}

